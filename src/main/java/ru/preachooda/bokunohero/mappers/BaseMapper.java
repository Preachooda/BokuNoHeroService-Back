package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.preachooda.bokunohero.utils.BaseUtils;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    default String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        return BaseUtils.formatDate(date);
    }

    @Named("dateTime")
    default String dateTimeToString(Date date) {
        if (date == null) {
            return null;
        }
        return BaseUtils.formatDateTime(date);
    }


    default Date stringToDate(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return BaseUtils.parseDate(s);
    }

    @Named("dateTime")
    default Date stringToDateTime(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return BaseUtils.parseDateTime(s);
    }

}
