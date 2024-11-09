public class BinarySearchTree {
    public class Node {
        char key;
        Node left;
        Node right;

        public Node (char key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public char getKey() {
            return key;
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

        public boolean isExistHelper (Node node, char key) {
            if (node == null) {
                return false;
            }
            if (key == node.getKey()) {
                return true;
            } else if (key > node.getKey()) {
                return isExistHelper(node.getRight(), key); // panggil method isExist
            } else {
                return isExistHelper(node.getLeft(),key);
            }
        }

        public boolean add (char key) {
            if (isExist(key)) {
                System.out.println("Karakter tersebut sudah tersedia");
                return false;
            }
            root = addHelper(root, key);
            return true;
        }

        public Node addHelper(Node node, char key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.getKey()) {
                node.setLeft(addHelper(node.getLeft(), key));
            } else if (key > node.getKey()) {
                node.setRight(addHelper(node.getRight(), key));
            }
            return node;
        }

        public boolean remove(char key) {
            if (!isExist(key)) {
                return false;
            }
            root = removeHelper(root, key);
            return true;
        }

        public Node removeHelper(Node node, char key) {
            if (node == null) {
                return node;
            }
            if (key < node.getKey()) {
                node.setLeft(removeHelper(node.getLeft(), key));
            } else if (key > node.getKey()) {
                node.setRight(removeHelper(node.getRight(), key));
            } else {
                if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                }
                char successorKey = minValue(node.getRight());
                node.key = successorKey;
                node.setRight(removeHelper(node.getRight(), successorKey));
            }
            return node;
        }

        public char minValue (Node root) {
            char minv = root.key;
            while (root.getLeft() != null) {
                minv = root.getLeft().key;
                root = root.left;
            }
            return minv;
        }

        public int height(Node node) {
            if (node == null) {
                return -1; // Jika node kosong, tinggi adalah -1
            } else {
                int leftHeight = height(node.getLeft());
                int rightHeight = height(node.getRight());

                return 1 + Math.max(leftHeight, rightHeight);
            }
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

            // jarak antara level
            space += 4;
            printTree(node.getRight(), space);
            System.out.println();
            for (int i = 4; i < space; i++) {
                System.out.print(" ");
            }
            System.out.println(node.getKey());
            printTree(node.getLeft(), space);
        }

    }
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Tree tree = binarySearchTree.new Tree();

        tree.add('S');
        tree.add('E');
        tree.add('K');
        tree.add('O');
        tree.add('L');
        tree.add('A');
        tree.add('H');

        System.out.println("Visualisasi Tree: ");
        tree.printTree(tree.root, 0);

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

        char removeKey = 'S';

        boolean isRemoved = tree.remove(removeKey);
        if (isRemoved) {
            System.out.println("key " + removeKey + " berhasil dihapus");
        } else {
            System.out.println("key " + removeKey + " tidak ditemukan");
        }

        System.out.println("\nHasil akhir tree");
        tree.printTree(tree.root, 0);

        // Tampilan tree setelah penghapusan elemenn
        System.out.println("\nTree setelah menghapus key :");
        tree.inOrderTransversal(tree.root);

        System.out.println("\nTinggi dari BST adalah: " + tree.height(tree.root));
    }
}


