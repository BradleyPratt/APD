package binaryTree;

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {
	TreeNode<T> root;
	//BTree<T> left, right;

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
		// TODO Auto-generated method stub
		return root.value;
	}

	@Override
	public BTree<T> left() {
		// TODO Auto-generated method stub
		return root.left;
	}

	@Override
	public BTree<T> right() {
		// TODO Auto-generated method stub
		return root.right;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.insert(1);
		tree.insert(0);
		tree.insert(2);

		Integer leftValue = tree.left().value();
		Integer rightValue = tree.right().value();
		System.out.println(tree.value());
		System.out.println(leftValue);
		System.out.println(rightValue);
	}
}
