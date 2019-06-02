package de.robatsky.fragmentdrawer.fragment;

import de.robatsky.fragmentdrawer.FragmentDrawer;
import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * <strong>Project:</strong> MikeRepository <br>
 * <strong>File:</strong> Fragment <br>
 *
 * @author Robert Hein
 * @version v1
 * @since 27.05.2019
 */
@DefaultProperty( value = "content" )
public class Fragment extends VBox {

    private FragmentActivationListener activationListener;

    private String title;
    private Node content;

    public Fragment( String title, Node content ) {
        this.title = title;
        this.content = content;
        this.getStyleClass().add(FragmentDrawer.FRAGMENT_DRAWER_NAVIGATION_ENTRY_CSS_CLASS);

        this.activationListener = ( ( drawer, source ) -> drawer.activateMenuById(source.getId()) );
    }

    public Fragment() {
        this("", null);
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        final Fragment fragment = ( Fragment ) o;
        return Objects.equals(getId(), fragment.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    public void setOnActivation( FragmentActivationListener activationListener ) {
        this.activationListener = activationListener;
    }

    public FragmentActivationListener getActivationListener() {
        return activationListener;
    }


    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Node getContent() {
        return content;
    }


    public void setContent( Node content ) {
        this.content = content;
        getChildren().clear();
        getChildren().add(content);
    }
}
