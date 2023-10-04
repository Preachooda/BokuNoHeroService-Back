package ru.preachooda.bokunohero.mappers;

import io.micrometer.common.util.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.preachooda.bokunohero.entity.EnumerationValue;
import ru.preachooda.bokunohero.utils.BaseUtils;

import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class BaseMapper {

    public String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        return BaseUtils.formatDate(date);
    }

    @Named("dateTime")
    public String dateTimeToString(Date date) {
        if (date == null) {
            return null;
        }
        return BaseUtils.formatDateTime(date);
    }


    public Date stringToDate(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return BaseUtils.parseDate(s);
    }

    @Named("dateTime")
    public Date stringToDateTime(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return BaseUtils.parseDateTime(s);
    }

    public EnumerationValue stringToEnumerationValue(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        EnumerationValue ev = new EnumerationValue();
        ev.setCode(s);
        return ev;
    }

}
