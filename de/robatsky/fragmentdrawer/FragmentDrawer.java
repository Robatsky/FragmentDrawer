package de.robatsky.fragmentdrawer;


import de.robatsky.fragmentdrawer.fragment.Fragment;
import de.robatsky.fragmentdrawer.fragment.FragmentActivationListener;
import de.robatsky.fragmentdrawer.fragment.FragmentListCell;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

/**
 * <strong>Project:</strong> MikeRepository <br>
 * <strong>File:</strong> FragmentDrawer <br>
 *
 * @author Robert Hein
 * @version v1
 * @since 27.05.2019
 */
@DefaultProperty( value = "fragments" )
public class FragmentDrawer extends BorderPane {

    public static final String FRAGMENT_DRAWER_CSS_CLASS = "fragmentdrawer";
    public static final String FRAGMENT_DRAWER_NAVIGATION_CSS_CLASS = "fragmentdrawer-menu";
    public static final String FRAGMENT_DRAWER_NAVIGATION_LEFT_RIGHT_CSS_CLASS = "left-right";
    public static final String FRAGMENT_DRAWER_NAVIGATION_TOP_BOTTOM_CSS_CLASS = "top-bottom";
    public static final String FRAGMENT_DRAWER_NAVIGATION_ENTRY_CSS_CLASS = "fragmentdrawer-menu-entry";
    public static final String FRAGMENT_DRAWER_NAVIGATION_ENTRY_ACTIVE_CSS_CLASS = "active";

    public enum MenuPosition {
        LEFT, RIGHT, TOP, BOTTOM
    }

    private ListView<Fragment> menu;
    private MenuPosition menuPosition;

    private ObservableList<Fragment> fragments;

    public FragmentDrawer() {
        this.fragments = FXCollections.observableArrayList();
        this.menuPosition = MenuPosition.LEFT;
        this.getStyleClass().add(FRAGMENT_DRAWER_CSS_CLASS);

        initializeAndDisplayMenu();
    }

    public void addFragment( Fragment fragment ) {
        fragments.add(fragment);
    }


    public void activateMenuById( String id ) {
        findFragmentById(id).ifPresent(fragment -> setCenter(fragment.getContent()));
    }

    public Optional<Fragment> findFragmentById( String id ) {
        return fragments.stream().filter(fragment -> fragment.getId().equals(id)).findFirst();
    }

    private void initializeAndDisplayMenu() {

        menu = new ListView<>(fragments);

        menu.getStyleClass().add(FRAGMENT_DRAWER_NAVIGATION_CSS_CLASS);
        menu.setCellFactory(param -> new FragmentListCell(this));

        determineMenuPosition();
    }

    private void determineMenuPosition() {
        switch ( menuPosition ) {
            case LEFT:
                setLeft(menu);
                menu.setOrientation(Orientation.VERTICAL);
                menu.setPrefWidth(200);
                break;
            case RIGHT:
                menu.setOrientation(Orientation.VERTICAL);
                menu.setPrefWidth(200);
                setRight(menu);
                break;
            case TOP:
                setTop(menu);
                menu.setOrientation(Orientation.HORIZONTAL);
                menu.setPrefHeight(50);
                break;
            case BOTTOM:
                setBottom(menu);
                menu.setOrientation(Orientation.HORIZONTAL);
                menu.setPrefHeight(50);
                break;
        }
    }

    public ObservableList<Fragment> getFragments() {
        return fragments;
    }


    public MenuPosition getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition( MenuPosition menuPosition ) {
        this.menuPosition = menuPosition;
        getChildren().remove(menu);
        determineMenuPosition();
    }

    public FragmentActivationListener getDefaultActivationListener() {
        return ( drawer, source ) -> setCenter(source);
    }

}
