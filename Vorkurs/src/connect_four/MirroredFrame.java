package connect_four;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class MirroredFrame extends JFrame {
	
	/**
	 * @see: http://stackoverflow.com/questions/11079348/how-to-evenly-resize-a-jframe-in-java-so-it-retains-its-squared-shape
	 */

    private static final long serialVersionUID = 1L;
    private State state;
    private Set<StateListener> listeners = new HashSet<StateListener>();

    public MirroredFrame(String name) {
        super(name);
        state = new State();
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                state.setSize(getSize(), true);
                Board.resizeImages();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addListener(StateListener sl) {
        listeners.add(sl);
    }

    public void associateWith(MirroredFrame other) {
        other.addListener(new MirrorStateListener());
    }

    private class State {

        private Dimension size;

        public void setSize(Dimension newSize, boolean fireEvent) {
            if (newSize.equals(size)) {
                return;
            }
            int height = newSize.height;
            int widht = newSize.width;
            if (height > widht) {
                size = new Dimension(height, height);
            } else {
                size = new Dimension(widht, widht);
            }
            MirroredFrame.this.setSize(size);
            if (fireEvent) {
                for (StateListener sl : listeners) {
                    sl.sizeChanged(size);
                }
            }
        }
    }

    private static interface StateListener {

        void sizeChanged(Dimension newSize);
    }

    private class MirrorStateListener implements StateListener {

        @Override
        public void sizeChanged(Dimension newSize) {
            state.setSize(newSize, false);
        }
    }
}