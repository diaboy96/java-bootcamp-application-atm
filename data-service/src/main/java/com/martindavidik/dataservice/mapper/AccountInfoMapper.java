package com.martindavidik.dataservice.mapper;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.dto.AccountInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountInfoMapper {

    @Mappings({
            @Mapping(target = "balance", source = "card.bankAccount.balance"),
            @Mapping(target = "name", source = "card.bankAccount.client.name"),
            @Mapping(target = "surname", source = "card.bankAccount.client.surname")
    })
    AccountInfoDTO mapDomainToDTO(Card card);
}
