package org.busmaxime.lwjgl;

import java.io.InputStream;
import junit.framework.Assert;
import org.junit.Test;

public class ImageLoaderTest {

    @Test
    public void should_load_image_from_classpath() {
        InputStream is = this.getClass().getResourceAsStream("/cross.png");

        Assert.assertNotNull(is);
    }
}
