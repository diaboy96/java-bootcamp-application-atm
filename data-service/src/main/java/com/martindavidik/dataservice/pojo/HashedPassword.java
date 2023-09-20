package com.martindavidik.dataservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HashedPassword {
    private byte[] hash;
    private byte[] salt;
}
