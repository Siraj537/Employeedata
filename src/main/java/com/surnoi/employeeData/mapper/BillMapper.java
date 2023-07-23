package com.surnoi.employeeData.mapper;

import com.surnoi.employeeData.dto.BillDTO;
import com.surnoi.employeeData.entities.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill billDToBill(BillDTO billDTO);

    @Mappings({
            @Mapping(target = "studentDTO.studentId", source = "student.studentId"),
            @Mapping(target = "studentDTO.name", source = "student.name")
    })
    BillDTO billToBillDTO(Bill bill);
}
