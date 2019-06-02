package de.robatsky.fragmentdrawer.fragment;

import de.robatsky.fragmentdrawer.FragmentDrawer;

/**
 * <strong>Project:</strong> MikeRepository <br>
 * <strong>File:</strong> FragmentActivationListener <br>
 *
 * @author rober
 * @version v1
 * @since 02.06.2019
 */
@FunctionalInterface
public interface FragmentActivationListener {
    void onActivation( FragmentDrawer drawer, Fragment source );
}
