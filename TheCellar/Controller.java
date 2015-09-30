/*
 * Controller.java
 * 
 * Version: 1
 * 
 * Revisions:
 *      Wrote empty methods.
 *      Coded methods to interact with model.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller for the game. Bridge between the game and the window.
 * 
 * @author Ben Woodworth
 */
public class Controller {
    private Map map;
    private Window w;

    public ListSelectionListener listListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            w.btnHall.setEnabled(w.lstHalls.getSelectedValue() != null);
            w.btnDrop.setEnabled(w.lstPlayerItems.getSelectedValue() != null);
            w.btnPickUp.setEnabled(w.lstRoomItems.getSelectedValue() != null);
        }
    };
    
    public MouseListener listClickListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() != 2) return;
            if (e.getSource() == w.lstHalls) {
                Hall hall = w.lstHalls.getSelectedValue();
                if (hall != null) map.player.enterHall(hall);
            } else if (e.getSource() == w.lstPlayerItems) {
                String item = w.lstPlayerItems.getSelectedValue();
                if (item != null) map.player.dropItem(item);
            } else if (e.getSource() == w.lstRoomItems) {
                String item = w.lstRoomItems.getSelectedValue();
                if (item != null) map.player.pickUpItem(item);
            }
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    };

    public ActionListener buttonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == w.btnHall) {
                Hall hall = w.lstHalls.getSelectedValue();
                if (hall != null) map.player.enterHall(hall);
            } else if (e.getSource() == w.btnDrop) {
                String item = w.lstPlayerItems.getSelectedValue();
                if (item != null) map.player.dropItem(item);
            } else if (e.getSource() == w.btnPickUp) {
                String item = w.lstRoomItems.getSelectedValue();
                if (item != null) map.player.pickUpItem(item);
            }
        }
    };
    
    /**
     * Registers listeners for a window.
     * 
     * @param map Map being controlled by the window.
     * @param window Window to register listeners for.
     */
    public Controller(Map map, Window window) {
        this.map = map;
        this.w = window;
        
        w.btnHall.addActionListener(buttonListener);
        w.btnDrop.addActionListener(buttonListener);
        w.btnPickUp.addActionListener(buttonListener);

        w.lstHalls.addMouseListener(listClickListener);
        w.lstPlayerItems.addMouseListener(listClickListener);
        w.lstRoomItems.addMouseListener(listClickListener);
        
        w.lstHalls.addListSelectionListener(listListener);
        w.lstPlayerItems.addListSelectionListener(listListener);
        w.lstRoomItems.addListSelectionListener(listListener);
    }
}
