/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import org.opencv.core.Core;

/**
 *
 * @author virajee
 */
public class ToolClassInSeparateJarInSharedDirectory {

    public static void loadNativeLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
