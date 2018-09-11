package com.ourlayer.helper;

import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;
import java.util.Collection;

import static org.apache.commons.lang3.Validate.isTrue;

@HandlebarsHelper
public class CustomHandlebarsHelper {

    private static final Logger log = LoggerFactory.getLogger(CustomHandlebarsHelper.class);

    public CharSequence eq(final Object obj, final Options opts) throws IOException {
        log.debug("eq : {}", obj);
        Object b = opts.param(0, null);
        boolean result = new EqualsBuilder().append(obj, b).isEquals();
        if (opts.tagType == TagType.SECTION) {
            return result ? opts.fn() : opts.inverse();
        }
        return result
                ? opts.hash("yes", true) + ""
                : opts.hash("no", false) + "";
    }

    public CharSequence cmp(final Object obj, final Options opts) throws IOException {

        String operator = opts.param(0, null);
        Object criteria = opts.param(1, null);

        isTrue(obj instanceof Comparable, "Can not compare : " + obj);
        isTrue(criteria instanceof Comparable, "Can not compare : " + criteria);

        final int i = ((Comparable) obj).compareTo(criteria);
        log.info(String.format("param : %s, cri : %s, result : %d", obj, criteria, i));
        if (opts.tagType == TagType.SECTION) {
            switch ( operator ) {
                case "<"  :
                    return i < 0 ? opts.fn() : opts.inverse();
                case "<=" :
                    return i <= 0 ? opts.fn() : opts.inverse();
                case ">"  :
                    return i > 0 ? opts.fn() : opts.inverse();
                case ">=" :
                    return i >= 0 ? opts.fn() : opts.inverse();
                case "==" :
                    return i == 0 ? opts.fn() : opts.inverse();
                case "!=" :
                    return i != 0 ? opts.fn() : opts.inverse();
                default   :
                    return opts.inverse();
            }
        }
        return opts.inverse();
    }

    public CharSequence size(final Object obj, final Options opts) throws IOException {
        log.debug("size : {}", obj);
        if (obj instanceof Collection) {
            return opts.hash("size", ((Collection) obj).size() + "");
        } else if (obj instanceof Object[]) {
            return opts.hash("size", ((Object[]) obj).length + "");
        } else {
            return opts.inverse();
        }
    }

}
