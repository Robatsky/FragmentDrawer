package de.robatsky.fragmentdrawer.fragment;

import de.robatsky.fragmentdrawer.FragmentDrawer;
import javafx.scene.control.ListCell;

/**
 * <strong>Project:</strong> MikeRepository <br>
 * <strong>File:</strong> FragmentListCell <br>
 *
 * @author Robert Hein
 * @version v1
 * @since 02.06.2019
 */
public class FragmentListCell extends ListCell<Fragment> {

    public FragmentListCell( FragmentDrawer drawer ) {
        setOnMouseClicked(event -> {
            getItem().getActivationListener().onActivation(drawer, getItem());
        });
    }

    @Override
    protected void updateItem( Fragment item, boolean empty ) {
        super.updateItem(item, empty);
        if ( item != null && !empty ) {
            setText(item.getTitle());
        } else {
            setText(null);
        }
    }
}
