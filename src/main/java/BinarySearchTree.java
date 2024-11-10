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
                char successorKey = minValue(node.getLeft());
                node.key = successorKey;
                node.setLeft(removeHelper(node.getLeft(), successorKey));
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
        Scanner scanner = new Scanner(System.in);
        int choice;

        char[] initialValues = {'G', 'J', 'S', 'D', 'K', 'C', 'A', 'F', 'B', 'N'};
        for (char value : initialValues) {
            tree.add(value);
        }

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert key");
            System.out.println("2. Remove key");
            System.out.println("3. Find key");
            System.out.println("4. Tree visualization");
            System.out.println("5. Print traversals");
            System.out.println("6. Height of tree");
            System.out.println("0. Close program");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    char insertKey = scanner.nextLine().charAt(0);
                    tree.add(insertKey);
                    break;
                case 2:
                    System.out.print("Enter key to remove: ");
                    char removeKey = scanner.nextLine().charAt(0);
                    if (tree.remove(removeKey)) System.out.println("Key " + removeKey + " removed.");
                    else System.out.println("Key " + removeKey + " not found.");
                    break;
                case 3:
                    System.out.print("Enter key to find: ");
                    char findKey = scanner.nextLine().charAt(0);
                    System.out.println("Key " + findKey + " exists: " + tree.isExist(findKey));
                    break;
                case 4:
                    System.out.println("Tree visualization:");
                    tree.printTree(tree.root, 0);
                    break;
                case 5:
                    System.out.println("Pre-order traversal:");
                    tree.preOrderTransversal(tree.root);
                    System.out.println("\nIn-order traversal:");
                    tree.inOrderTransversal(tree.root);
                    System.out.println("\nPost-order traversal:");
                    tree.postOrderTransversal(tree.root);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Height of tree: " + tree.height(tree.root));
                    break;
                case 0:
                    System.out.println("Closing program.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}


