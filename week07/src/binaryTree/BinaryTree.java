package binaryTree;

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {
	TreeNode<T> root;

	@Override
	public void insert(T value) {
		if (root == null) {
				root = new TreeNode<T>(value);
			} else if (value.compareTo(value()) < 0) {
				root.left().insert(value);
			} else {
				root.right().insert(value);
			}
	}

	@Override
	public T value() {
		return root.value;
	}

	@Override
	public BTree<T> left() {
		return root.left;
	}

	@Override
	public BTree<T> right() {
		return root.right;
	}
}
