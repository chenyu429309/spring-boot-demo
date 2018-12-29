package com.felix.springbootdemo.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinarySearchTree<T extends Comparable<?
        super T>> {

    private BinaryNode<T> mRoot;

    public void makeEmpty() {
        mRoot = null;
    }

    public boolean isEmpty() {
        return mRoot == null;
    }

    public boolean contains(T t) {
        return contains(t, mRoot);
    }

    public boolean contains(T t, BinaryNode<T> node) {
        if (t == null) {
            return false;
        }

        int campareResult = t.compareTo(node.element);
        if (campareResult < 0) {
            return contains(t, node.left);
        } else if (campareResult > 0) {
            return contains(t, node.right);
        } else {
            return true;
        }
    }

    public T findMax() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return findMax(mRoot).element;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return findMin(mRoot).element;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
        }
        return node;
    }

    public void insert(T t) {
        mRoot = insert(t, mRoot);
    }

    public BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null) {

            return new BinaryNode<T>(t, null, null);
        }

        int compareResult = t.compareTo(node.element);
        if (compareResult > 0) {
            node.right = insert(t, node.right);
        } else if (compareResult < 0) {
            node.left = insert(t, node.left);
        }

        return node;
    }

    public void remove(T t) {
        mRoot = remove(t, mRoot);
    }

    public BinaryNode<T> remove(T t, BinaryNode<T> node) {
        if (node == null) {
            return node;
        }
        int compareResult = t.compareTo(node.element);
        if (compareResult > 0) {
            node.right = remove(t, node.right);
        } else if (compareResult < 0) {
            node.left = remove(t, node.left);
        } else if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;

    }

    private static class BinaryNode<T> {
        private T element;
        private BinaryNode<T> left;// 左孩子
        private BinaryNode<T> right;// 右孩子

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public BinaryNode(T element) {
            this(element, null, null);
        }

    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree=new BinarySearchTree();
        binarySearchTree.insert(10);
        binarySearchTree.insert(11);
        binarySearchTree.insert(13);
        binarySearchTree.insert(12);
        binarySearchTree.insert(14);
        binarySearchTree.remove(11);
    }
}