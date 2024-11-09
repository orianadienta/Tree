public class RedBlackTree {
    public class Node {
        char key;
        Node left;
        Node right;
        Node parent;
        boolean red;

        public Node(char key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.red = true;
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
    }

    public class Tree {
        Node root;

        public Tree() {
            this.root = null;
        }

        public boolean isExist(char key) {
            return isExistHelper(root, key);
        }

        public boolean isExistHelper(Node node, char key) {
            if (node == null) {
                return false;
            }
            if (key == node.getKey()) {
                return true;
            } else if (key < node.getKey()) {
                return isExistHelper(node.getLeft(), key); // panggil method isExist
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

            if (root == null) {
                root = newNode;
                root.setRed(false);
            } else {
                Node node = root;
                Node parent = null;
                while (node != null) {
                    parent = node;
                    if (key < node.getKey()) {
                        node = node.getLeft();
                    } else {
                        node = node.getRight();
                    }
                }
                if (key < parent.getKey()) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                newNode.setParent(parent);
                fixAdd(newNode);
            }
            return true;
        }

        private void fixAdd(Node node) {
            while (node != root && node.getParent().isRed()) {
                if (node.getParent() == node.getParent().getParent().getLeft()) {
                    Node uncle = node.getParent().getParent().getRight();
                    if (uncle != null && uncle.isRed()) {
                        // recoloring
                        node.getParent().setRed(false);
                        uncle.setRed(false);
                        node.getParent().getParent().setRed(true);
                        node = node.getParent().getParent();
                    } else {
                        if (node == node.getParent().getRight()) {
                            // rotasi kiri pada parent
                            node = node.getParent();
                            rotateToLeft(node);
                        }
                        // rotasi kanan pada kakek
                        node.getParent().setRed(false);
                        node.getParent().getParent().setRed(true);
                        rotateToRight(node.getParent().getParent());
                    }
                } else {
                    Node uncle = node.getParent().getParent().getLeft();
                    if (uncle != null && uncle.isRed()) {
                        // recoloring
                        node.getParent().setRed(false);
                        uncle.setRed(false);
                        node.getParent().getParent().setRed(true);
                        node = node.getParent().getParent();
                    } else {
                        if (node == node.getParent().getLeft()) {
                            // rotasi kanan parent
                            node = node.getParent();
                            rotateToRight(node);
                        }
                        // rotasi kiri kakek
                        node.getParent().setRed(false);
                        node.getParent().getParent().setRed(true);
                        rotateToLeft(node.getParent().getParent());
                    }
                }
            }
            root.setRed(false); // root selalu hitam
        }

        public void rotateToLeft(Node node) {
            Node rightChild = node.getRight();
            node.setRight(rightChild.getLeft());

            if (rightChild.getLeft() != null) {
                rightChild.getLeft().setParent(node);
            }

            rightChild.setParent(node.getParent());

            if (node.getParent() == null) {
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

            if (leftChild.getRight() != null) {
                leftChild.getRight().setParent(node);
            }
            leftChild.setParent(node.getParent());

            if (node.getParent() == null) {
                root = leftChild;
            } else if (node == node.getParent().getRight()) {
                node.getParent().setRight(leftChild);
            } else {
                node.getParent().setLeft(leftChild);
            }
            leftChild.setRight(node);
            node.setParent(leftChild);
        }

        public void preOrderTransversal(Node node) {
            if (node != null) {
                System.out.print(node.getKey() + " ");
                preOrderTransversal(node.getLeft());
                preOrderTransversal(node.getRight());
            }
        }

        public void inOrderTransversal(Node node) {
            if (node != null) {
                inOrderTransversal(node.getLeft());
                System.out.print(node.getKey() + " ");
                inOrderTransversal(node.getRight());
            }
        }

        public void postOrderTransversal(Node node) {
            if (node != null) {
                postOrderTransversal(node.getLeft());
                postOrderTransversal(node.getRight());
                System.out.print(node.getKey() + " ");
            }
        }

        public void printTree(Node node, int space) {
            if (node == null) return;
            space += 4;
            printTree(node.getRight(), space);
            System.out.println();
            for (int i = 4; i < space; i++) {
                System.out.print(" ");
            }

            if (node.isRed()) {
                System.out.print("\033[1;31m" +node.getKey() + "\033[0m");
            } else {
                System.out.print(node.getKey());
            }
            printTree(node.getLeft(), space);
        }
    }
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        Tree tree = redBlackTree.new Tree();

        tree.add('G');
        tree.add('J');
        tree.add('S');
        tree.add('D');
        tree.add('K');
        tree.add('C');
        tree.add('A');
        tree.add('F');
        tree.add('B');
        tree.add('N');

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





