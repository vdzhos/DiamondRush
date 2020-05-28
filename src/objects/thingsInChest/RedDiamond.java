package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;
import java.awt.*;

public class RedDiamond extends ThingInChest{

    public int quantity;

    public RedDiamond(int quantity){
        image = new ImageIcon("statusBar/diamondHexRed.png").getImage();
        this.quantity = quantity;
    }

    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.numberOfRedDiamondsCollected += quantity;
        playPanel.updateNumberOfRedDiamondsOnStatusBar();
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
        g2.setFont(font);
        g2.setPaint(Color.WHITE);
        int shift = quantity<10?28:16;
        g2.drawString(""+quantity,x+shift,y+62);
    }
}
