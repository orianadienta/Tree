public class RedBlackTree {
    public class Node {
        char key;
        Node left;
        Node right;
        Node parent;
        boolean red;
        boolean isLeaf;

        public Node() {
            this.red = false; // leaf selalu hitam
            this.isLeaf = true;
        }

        public Node(char key) {
            this.key = key;
            this.red = true; // node baru selalu merah
            this.isLeaf = false;
            this.left = new Node();
            this.right = new Node();
        }

        public char getKey() {
            return key;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getParent() {
            return parent;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public boolean isLeaf() {
            return isLeaf;
        }
    }

    public class Tree {
        Node root;
        Node leaf;

        public Tree() {
            leaf= new Node();
            leaf.setRed(false);
            this.root = leaf;
        }

        public boolean isExist(char key) {
            return isExistHelper(root, key);
        }

        public boolean isExistHelper(Node node, char key) {
            if (node.isLeaf()) {
                return false;
            }
            if (key == node.getKey()) {
                return true;
            } else if (key < node.getKey()) {
                return isExistHelper(node.getLeft(), key);
            } else {
                return isExistHelper(node.getRight(), key);
            }
        }

        public boolean add(char key) {
            if (isExist(key)) {
                return false;
            }
            Node newNode = new Node(key);
            newNode.setRed(true);

            if (root.isLeaf()) {
                root = newNode;
                root.setRed(false);
                root.setParent(leaf);
            } else {
                Node node = root;
                Node parent = leaf;
                while (!node.isLeaf()) {
                    parent = node;
                    if (key < node.getKey()) {
                        node = node.getLeft();
                    } else {
                        node = node.getRight();
                    }
                }
                newNode.setParent(parent);
                if (key < parent.getKey()) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                fixAdd(newNode);
            }
            return true;
        }

        private void fixAdd(Node node) {
            while (node.getParent().isRed()) {
                if (node.getParent() == node.getParent().getParent().getLeft()) {
                    Node uncle = node.getParent().getParent().getRight();
                    if (uncle.isRed()) {
                        node.getParent().setRed(false);
                        uncle.setRed(false);
                        node.getParent().getParent().setRed(true);
                        node = node.getParent().getParent();
                    } else {
                        if (node == node.getParent().getRight()) {
                            node = node.getParent();
                            rotateToLeft(node);
                        }
                        node.getParent().setRed(false);
                        node.getParent().getParent().setRed(true);
                        rotateToRight(node.getParent().getParent());
                    }
                } else {
                    Node uncle = node.getParent().getParent().getLeft();
                    if (uncle.isRed()) {
                        node.getParent().setRed(false);
                        uncle.setRed(false);
                        node.getParent().getParent().setRed(true);
                        node = node.getParent().getParent();
                    } else {
                        if (node == node.getParent().getLeft()) {
                            node = node.getParent();
                            rotateToRight(node);
                        }
                        node.getParent().setRed(false);
                        node.getParent().getParent().setRed(true);
                        rotateToLeft(node.getParent().getParent());
                    }
                }
                if (node == root) {
                    break;
                }
            }
            root.setRed(false);
        }

        public void rotateToLeft(Node node) {
            Node rightChild = node.getRight();
            node.setRight(rightChild.getLeft());

            if (!rightChild.getLeft().isLeaf()) {
                rightChild.getLeft().setParent(node);
            }

            rightChild.setParent(node.getParent());

            if (node.getParent().isLeaf()) {
                root = rightChild;
            } else if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(rightChild);
            } else {
                node.getParent().setRight(rightChild);
            }
            rightChild.setLeft(node);
            node.setParent(rightChild);
        }

        public void rotateToRight(Node node) {
            Node leftChild = node.getLeft();
            node.setLeft(leftChild.getRight());

            if (!leftChild.getRight().isLeaf()) {
                leftChild.getRight().setParent(node);
            }
            leftChild.setParent(node.getParent());

            if (node.getParent().isLeaf()) {
                root = leftChild;
            } else if (node == node.getParent().getRight()) {
                node.getParent().setRight(leftChild);
            } else {
                node.getParent().setLeft(leftChild);
            }
            leftChild.setRight(node);
            node.setParent(leftChild);
        }

        // Metode traversal dan print
        public void preOrderTransversal(Node node) {
            if (!node.isLeaf()) {
                System.out.print(node.getKey() + " ");
                preOrderTransversal(node.getLeft());
                preOrderTransversal(node.getRight());
            }
        }

        public void inOrderTransversal(Node node) {
            if (!node.isLeaf()) {
                inOrderTransversal(node.getLeft());
                System.out.print(node.getKey() + " ");
                inOrderTransversal(node.getRight());
            }
        }

        public void postOrderTransversal(Node node) {
            if (!node.isLeaf()) {
                postOrderTransversal(node.getLeft());
                postOrderTransversal(node.getRight());
                System.out.print(node.getKey() + " ");
            }
        }

        public void printTree(Node node, int space) {
            if (node.isLeaf()) return;
            space += 4;
            printTree(node.getRight(), space);
            System.out.println();
            for (int i = 4; i < space; i++) {
                System.out.print(" ");
            }

            if (node.isRed()) {
                System.out.print("\033[1;31m" + node.getKey() + "\033[0m");
            } else {
                System.out.print(node.getKey());
            }
            printTree(node.getLeft(), space);
        }
    }
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        Tree tree = redBlackTree.new Tree();

        tree.add('C');
        tree.add('A');
        tree.add('D');
        tree.add('F');
        tree.add('B');
        tree.add('E');
        tree.add('G');
        tree.add('I');
        tree.add('H');
        tree.add('J');
        tree.add('J');

        System.out.println("Visualisasi Tree: ");
        tree.printTree(tree.root, 0);

        System.out.println();

        System.out.println("Pre order: ");
        tree.preOrderTransversal(tree.root);
        System.out.println();

        System.out.println("In order: ");
        tree.inOrderTransversal(tree.root);
        System.out.println();

        System.out.println("Post order: ");
        tree.postOrderTransversal(tree.root);
        System.out.println();

        char searchKey = 'O';
        System.out.println("\nApakah " + searchKey + " ada di dalam tree? " + tree.isExist(searchKey));
        System.out.println();
    }
}
