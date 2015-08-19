package com.nuance.vdmach.common.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.nuance.vdmach.common.vo.ItemDTO;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author ediband1
 *         date:   8/18/15 2:33 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class VOUtilTest {
    @Test
    public void convertToDTO_ReturnsEmptyDTOWhenEntityIsNull() throws Exception {
        assertNotNull(VOUtil.convertToVO(null, ItemDTO.class));
    }

    @Test
    public void convertToEntity_ReturnsEmptyEntityWhenDTOIsNull() throws Exception {
        assertNotNull(VOUtil.convertToEntity(null, ItemDTO.class));
    }

}