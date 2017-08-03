package com.view_anniation.tree;

/**
 * Created by peiboning on 2017/7/27.
 */

public class TreeNode {
    public int data;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isYezi(){
        return parent != null && left == null && right == null;
    }

}
