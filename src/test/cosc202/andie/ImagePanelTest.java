package test.cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.ImagePanel;
import java.awt.*;

public class ImagePanelTest {
    @Test
    void initialDummyTest() {

    }

    @Test 
    void getZoomInitialValue() {
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
        //Assertions.assertEquals(-1, testPanel.getZoom());
    }

    @Test
    void getZoomAfterSetZoom(){
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom() == 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }

    @Test
    void getPreferredSize(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(testPanel.getPreferredSize(), new Dimension(450, 450));
    }
}
