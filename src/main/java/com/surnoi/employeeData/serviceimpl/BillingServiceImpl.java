package com.surnoi.employeeData.serviceimpl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.surnoi.employeeData.dto.BillDTO;
import com.surnoi.employeeData.dto.ResponseDTO;
import com.surnoi.employeeData.entities.Bill;
import com.surnoi.employeeData.entities.Course;
import com.surnoi.employeeData.entities.Student;
import com.surnoi.employeeData.mapper.BillMapper;
import com.surnoi.employeeData.repositories.BillRepository;
import com.surnoi.employeeData.repositories.StudentRepository;
import com.surnoi.employeeData.service.BillingService;
import com.surnoi.employeeData.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Slf4j
@Service
public class BillingServiceImpl implements BillingService {

    private final StudentRepository studentRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillingServiceImpl(StudentRepository studentRepository, BillRepository billRepository, BillMapper billMapper) {
        this.studentRepository = studentRepository;
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    @Override
    public ResponseEntity<ResponseDTO> generateBill(String studentId) {
        log.info("Generating the bill");
        try {
            if(StringUtils.isNotBlank(studentId)){
                Optional<Student> studentOptional = studentRepository.findById(Integer.parseInt(studentId));
                if(studentOptional.isPresent()){
                    Student student = studentOptional.get();
                    List<Course> courses = student.getCourses();
                    ResponseEntity<ResponseDTO> billResponse = createBill(courses, student);
                    student.setBillGenerated(true);
                    studentRepository.save(student);
                    return billResponse;
                }
                else {
                    log.info("No student with given Id ");
                    return ResponseUtils.getResponseEntity("No student with given Id ", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                log.info("Invalid StudentId ");
                return ResponseUtils.getResponseEntity("Invalid StudentId ", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.getResponseEntity(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> downloadBill(UUID billId) throws DocumentException, TransformerConfigurationException {
        log.info("Creating the bill pdf document ");
        Optional<Bill> billOptional = billRepository.findById(billId);
        String filename;
        if(billOptional.isPresent()){
            Bill bill = billOptional.get();
            filename = "Bill_"+billId;
            String data = "Bill Id : "+bill.getBid()+"\n"+"Name : "+ bill.getStudent().getName() +"\n"+
                    "Contact Number : "+bill.getStudent().getContactNumber()+"\n"+"Email : "+bill.getStudent().getEmail()+"\n"+
                    "Address : "+bill.getStudent().getAddress();

            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document,byteArrayOutputStream);
            document.open();
            createBorderInDocument(document);
            Paragraph p = new Paragraph("Student Management System",createFontForDocument("Header"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(data+"\n \n",createFontForDocument("Data")));
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            createTableHeader(table);
            createRows(table,bill.getCourses());
            document.add(table);
            Paragraph footer = new Paragraph("Total Amount : "+bill.getTotalAmount()+"\n"+
                    "Total Paid : "+bill.getPaidAmount()+"\n"+
                    "Total Pending : "+bill.getTotalPending(),createFontForDocument("Data")
                    );
            document.add(footer);
            document.close();
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseUtils.getResponseForBill(HttpStatus.OK,"Bill Downloaded Successfully",pdfBytes);
        }
        else {
            return ResponseUtils.getResponseEntity("Invalid bill id ", HttpStatus.BAD_REQUEST);
        }
    }

    private void createRows(PdfPTable table, List<Course> courses) {
        log.info("Creating rows for the table");
        int i = 1;
        for(Course c : courses){
            table.addCell(i+++"");
            table.addCell(c.getCourseId()+"");
            table.addCell(c.getCourseName());
            table.addCell(c.getFee()+"");
        }
    }

    private void createTableHeader(PdfPTable table) {
        log.info("Creating table header");
        Stream.of("No.","Course Id","Course Name","Course Fee").forEach(title -> {
            PdfPCell header = new PdfPCell();
            //header.setBackgroundColor();
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(title));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }

    private Font createFontForDocument(String type) {
        log.info("creating font for : "+type);
        switch (type){
            case "Header": {
                Font head = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                head.setStyle(Font.BOLD);
                return head;
            }
            case "Data": {
                Font data = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, BaseColor.BLACK);
                data.setStyle(Font.BOLD);
                return data;
            }
            default: return new Font();
        }
    }

    private void createBorderInDocument(Document document) throws DocumentException {
        log.info("Creating the border in the bill document");
        Rectangle rectangle = new Rectangle(577,825,18,15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBackgroundColor(BaseColor.WHITE);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private ResponseEntity<ResponseDTO> createBill(List<Course> courses, Student student) {
        log.info("Creating the bill ");
        BillDTO billDTO;
        if(student.isBillGenerated()){
            log.info("Bill is already created and returning the generated bill ");
            Comparator<Bill> c = Comparator.comparing(Bill::getBillGeneratedTime);
            Optional<Bill> billOptional = billRepository.findByStudentId(student.getStudentId()).stream().max(c);
            billDTO = billMapper.billToBillDTO(billOptional.get());
        }
        else {
            log.info("Bill is not available ");
            Bill bill = new Bill();
            bill.setBid(UUID.randomUUID());
            log.info("Bill Id: {} ",bill.getBid());
            bill.setCourses(courses);
            bill.setStudent(student);
            bill.setBillGeneratedTime(LocalDateTime.now());
            bill.setContactNumber(student.getContactNumber());
            List<Bill> billList = billRepository.findByStudentId(student.getStudentId());
            double totalAmount = courses.stream().map(Course::getFee).reduce(0.0, Double::sum);
            double totalPending = totalAmount - billList.stream().map(Bill::getPaidAmount).reduce(0.0, Double::sum);
            bill.setTotalAmount(totalAmount);
            bill.setTotalPending(totalPending);
            Bill saveBill = billRepository.save(bill);
            billDTO = billMapper.billToBillDTO(saveBill);
            log.info("Created the new bill ");
        }
        return ResponseUtils.getResponseForBill(HttpStatus.OK, "Bill is generated", billDTO);
    }
}
