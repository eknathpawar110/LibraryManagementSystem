package com.example.Library.Management.System.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDepartmentRequest {
    private int libraryId;
    private String departmentName;
}
