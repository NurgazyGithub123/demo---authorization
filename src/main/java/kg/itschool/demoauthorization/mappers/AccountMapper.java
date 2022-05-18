package kg.itschool.demoauthorization.mappers;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.entitys.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountDto>{

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
}
