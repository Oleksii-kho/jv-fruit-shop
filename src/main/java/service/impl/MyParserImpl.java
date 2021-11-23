package service.impl;

import model.TransactionDto;
import service.MyParser;
import service.Validator;

import java.util.ArrayList;
import java.util.List;

public class MyParserImpl implements MyParser<TransactionDto> {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int FRUIT_NAME_INDEX = 1;
    private static final int FRUIT_AMOUNT_INDEX = 2;
    private final Validator validator;

    public MyParserImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<TransactionDto> parseLine(List<String> list) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        list.remove(0);
        for (String line : list) {
            validator.validate(line);
            String[] information = line.split(",");
            transactionDtoList.add(new TransactionDto(information[OPERATION_TYPE_INDEX],
                    information[FRUIT_NAME_INDEX],
                    Integer.parseInt(information[FRUIT_AMOUNT_INDEX])));
        }
        return transactionDtoList;
    }
}
