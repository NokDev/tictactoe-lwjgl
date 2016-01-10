package org.busmaxime.lwjgl;

import junit.framework.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ImageLoaderTest {

    @Test
    public void should_load_image_from_classpath() {
        InputStream is = this.getClass().getResourceAsStream("/cross.png");

        Assert.assertNotNull(is);
    }
}
