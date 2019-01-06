package cabernet1.monopoly.utils.animation;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Purpose:	This class extends the Generic Animator of
 * Program 7.3c to add a control panel to
 * control the speed, and add some buttons.
 */
public class Animator implements Runnable {
    private Vector<Animatable> elementsToDraw = new Vector<>();
    private long sleepTime = 20;
    private boolean animatorStopped = true, firstTime = true;

    /**
     * Constructor that creates the JFrame for the animator.  Note
     * the JFrame is shown in the show() method because this starts
     * the GUI thread.  Starting the thread in the constructor
     * can lead to a race condition.
     */
    public Animator() {
    }

    /**
     * setVisible is called to display or hide the animator.
     * Note that only display = true is implemented, and this
     * function only works at this point if it is called once.
     * It is left as an exercise to implement it correctly.
     * If display = false, the Control Thread needs to be
     * suspended.  If display = true, the control thread
     * should be started only the first time, after that it
     * should be unsuspended.  This can be accomplished as
     * using control variables in the paint method for Program
     * 7.4 and after, and should not be done using the suspend
     * and resume methods.
     */
    public void setVisible(boolean display) {
        if (display) {
            if (firstTime) {
                firstTime = false;
                // Put the animator in another thread so that the
                // calling object can continue.
                (new Thread(this)).start();
            }
        }
    }


    /**
     * Thread that runs the animator.  This thread sleeps for some
     * amount of time specified by sleepTime, then calls repaint
     * which forces a call to the paint method, but in the GUI thread.
     * Note that the animatorStopped button allows the animator to
     * single step and pause the animation.  The notify is done in
     * the control frame from the button.
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
            animate();
        }
    }

    public void animate() {
        Enumeration<Animatable> e = elementsToDraw.elements();
        while (e.hasMoreElements())
            e.nextElement().animate();
    }

    /**
     * addElement adds each drawable to the vector for use by the
     * DrawElements method.
     */
    public void addDrawable(Animatable d) {
        elementsToDraw.addElement(d);
        //System.out.println("Added drawable: "+elementsToDraw.size());
    }

    /**
     * removeElement is used to remove drawables from the vector.
     */
    public void removeDrawable(Animatable d) {

        elementsToDraw.removeElement(d);
        //System.out.println("Removed drawable: "+elementsToDraw.size());
    }


}
