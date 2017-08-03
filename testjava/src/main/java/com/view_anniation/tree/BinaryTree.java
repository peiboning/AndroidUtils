package com.view_anniation.tree;

/**
 * Created by peiboning on 2017/7/27.
 */

public class BinaryTree {
    public static void run(){
        BinaryTree tree = new BinaryTree();
        tree.contruct();
        tree.midIndexprint(tree.root);
        tree.delete(5);
        System.out.print("\n");
        tree.midIndexprint(tree.root);

    }

    private TreeNode root;

    private void contruct(){
        int[] arr = {2,3,100, 5, 10, 30, 20, 15};
        for(int i : arr){
            insertNode(i);
        }
//        System.out.print("前序遍历\n");
//        preIndexprint(root);
//        System.out.print("\n后序遍历\n");
//        lastIndexprint(root);
//        System.out.print("\n中序遍历\n");
//        midIndexprint(root);
    }

    private void insertNode(int value){
        if(null == root){
            root = new TreeNode();
            root.data = value;
        }else{
            TreeNode current = root;
            TreeNode parent = null;
            TreeNode node = new TreeNode();
            node.data = value;
            while (true){
                parent = current;
                if(current.data > value){
                    current = current.left;
                    if(current == null){
                        parent.left = node;
                        break;
                    }
                }else{
                    current = current.right;
                    if(current == null){
                        parent.right = node;
                        break;
                    }
                }
            }
        }
    }

    private void preIndexprint(TreeNode node){
        if(null == node){
            return;
        }
        preIndexprint(node.left);
        System.out.print(node.data+",");
        preIndexprint(node.right);
    }

    private void lastIndexprint(TreeNode node){
        if(null == node){
            return;
        }
        lastIndexprint(node.right);
        System.out.print(node.data+",");
        lastIndexprint(node.left);
    }

    private void midIndexprint(TreeNode node){
        if(null == node){
            return;
        }
        System.out.print(node.data+",");
        midIndexprint(node.left);
        midIndexprint(node.right);
    }

    private boolean delete(int value){
        if(root == null){
            return false;
        }
        TreeNode targetNode = null;
        TreeNode current = root;
        while (true){
            if(current.data==value){
                if(current.left != null){
                    current.left.parent = current.parent;
                }
                if(current.right != null){
                    current.right.parent = current.parent;
                }

                current.left = null;
                current.right = null;
                current.parent = null;
                return true;
            }

            if(current.data>value){
                current = current.left;
            }else {
                current = current.right;
            }

            if(current == null){
                break;
            }

        }

        return false;
    }


}
