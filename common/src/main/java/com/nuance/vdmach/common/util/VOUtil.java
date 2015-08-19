package com.nuance.vdmach.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Utility class for copying data to and from DTO object. It works good on primitives fields but more complex fields (objects and collections) should
 * be mapped by hand i.e. no getter and setter should be defined for these fields so that they are ignored by {@link org.springframework.beans.BeanUtils#copyProperties}
 *
 * @author ediband1
 *         date:   8/18/15 2:09 PM
 */
public class VOUtil {

    private static final Logger logger = LoggerFactory.getLogger(VOUtil.class);

    /**
     * Leverages on the spring BeanUtils.copyProperties to copy data from {@code entity} to {@code dto}. Only the exposed properties (with getter and
     * setter) will be copied.
     * @param entity
     * @param voType
     * @param <D>
     * @param <E>
     * @return an empty DTO if {@code entity} is null otherwise a DTO containing the date of {@code entity}
     */
    public static <D, E> D convertToVO(E entity, Class<D> voType) {
        try {
            D dto = voType.newInstance();

            if (entity != null) {
                BeanUtils.copyProperties(entity, dto);
            }
            return  dto;

        } catch (Exception e) {
            logger.error("Exception while converting to VO", e);
            return null;
        }
    }

    /**
     * Leverages on the spring BeanUtils.copyProperties to copy data from from {@code vo} to {@code entity}. Only the exposed properties (with
     * getter and setter) will be copied.
     * @param vo
     * @param entityType
     * @param <D>
     * @param <E>
     * @return
     */
    public static <D, E> E convertToEntity(D vo, Class<E> entityType) {
        try {
            E entity = entityType.newInstance();

            if (vo != null) {
                BeanUtils.copyProperties(vo, entity);
            }

            return  entity;

        } catch (Exception e) {
            logger.error("Exception while converting from VO", e);
            return null;
        }
    }

}
