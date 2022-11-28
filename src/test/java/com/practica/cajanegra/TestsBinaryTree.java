package com.practica.cajanegra;

/*
Métodos a probar:
getRoot(): Node<T>
size(): int
insert(content: T, atNode: Node<T>, atLeft: boolean): void
search(targetContent: T): Node<T>
remove(node: Node<T>): void
equals(binaryTree: BinaryTree): boolean
toList(): ArrayList<T>
iterator(): Iterator<T>
getSubTree(node: Node<T>): BinaryTree<T>
depth(): int
depth(node: Node<T>): int
toString(): String
(los siguientes no sé porque son privados)
recursiveSearch(content: T, atNode: Node<T>)
recursiveInsert(newNode: Node<T>, atNode: Node<T>, boolean: atLeft)
 */

import com.binarytree.BinaryTree;
import com.binarytree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestsBinaryTree {
    @Test
    public void getRootTest() {
        /*
        Clases aceptables -> Árbol con una o más capas.
        Clases no aceptables -> Árbol sin capas (imposible probar ya que no se puede instanciar un arbol sin nodo).
         */
        BinaryTree testTree = new BinaryTree("1");
        Assertions.assertNotNull(testTree.getRoot());  //Falla el test
        Assertions.assertEquals("1",testTree.getRoot().getContent().toString()); //Funciona el test

        //Añadimos nodos para una segunda capa
        Node node1 = new Node("2");
        Node node2 = new Node("3");
        testTree.insert(node1.getContent(), testTree.getRoot(),false);
        testTree.insert(node2.getContent(), testTree.getRoot(),true);
        Assertions.assertEquals("1",testTree.getRoot().getContent().toString()); //Funciona el test

        /*
        La función cumple su funcionalidad
         */
    }

    @Test
    public void sizeTest() {
        /*
        Clases aceptables -> Árbol con capas solo a la izquierda,
                             árbol con capas solo a la derecha,
                             árbol con capas a los dos lados,
                             árbol con un solo nodo.
        Clases no aceptables -> Árbol sin nodos (imposible probar ya que no se puede instanciar un árbol sin nodo).
         */

        BinaryTree testTreeOne = new BinaryTree("1");
        Assertions.assertEquals(1,testTreeOne.size()); //Funciona el test

        BinaryTree testTreeLeft = new BinaryTree("1");
        Node node1Left = new Node("2");
        Node node2Left = new Node("3");
        testTreeLeft.insert(node1Left.getContent(), testTreeLeft.getRoot(),true);
        testTreeLeft.insert(node2Left.getContent(),node1Left, true);
        Assertions.assertEquals(3, testTreeLeft.size()); // Falla el test, se debería recibir 3, pero se obtiene 2.

        BinaryTree testTreeRight = new BinaryTree("1");
        Node node1Right = new Node("2");
        Node node2Right = new Node("3");
        testTreeRight.insert(node1Right.getContent(),testTreeRight.getRoot(),false);
        testTreeLeft.insert(node2Right.getContent(),node1Right, false);
        Assertions.assertEquals(3, testTreeRight.size()); // Falla el test, se debería recibir 3 pero se obtiene 2.

        BinaryTree testTreeBoth = new BinaryTree("1");
        Node node1Both = new Node("2");
        Node node2Both = new Node("3");
        Node node3Both = new Node("4");
        Node node4Both = new Node("5");
        Node node5Both = new Node("6");
        Node node6Both = new Node("7");
        testTreeBoth.insert(node1Both.getContent(),testTreeBoth.getRoot(),false);
        testTreeBoth.insert(node2Both.getContent(),testTreeBoth.getRoot(),true);
        testTreeBoth.insert(node3Both.getContent(),node1Both,false);
        testTreeBoth.insert(node4Both.getContent(),node1Both, true);
        testTreeBoth.insert(node5Both.getContent(),node2Both, false);
        testTreeBoth.insert(node6Both.getContent(),node2Both,true);
        Assertions.assertEquals(7, testTreeBoth.size()); // Falla el test, se debería recibir 7 pero se obtiene 3

        /*
        Esta función no cumple su funcionalidad, ya que los resultados que devuelve no son acordes a la descripción de
        la función.
         */
    }

    @Test
    public void insertTest() {

        /*
        Clases aceptables -> Insertar nodo a la izquierda de uno que no tenga hijo a la izquierda,
                             insertar nodo a la derecha de uno que no tenga hijo a la derecha,
                             insertar nodo a la izquierda de uno que tiene hijo en esa posición,
                             insertar nodo a la derecha de uno que tiene hijo en esa posición.
         */

        BinaryTree testTree = new BinaryTree("1");
        Node node1 = new Node("2");
        Node node2 = new Node("3");

        //insertamos en la izquierda sin que haya nodo en esa posición
        testTree.insert(node1.getContent(), testTree.getRoot(), true);
        Assertions.assertEquals("2", testTree.getRoot().getLeftChild().getContent().toString()); // Pasa el test

        //insertamos en la derecha sin que haya nodo en esa posición
        testTree.insert(node2.getContent(), testTree.getRoot(), false);
        Assertions.assertEquals("3",testTree.getRoot().getRightChild().getContent().toString()); // Pasa el test

        Node node3 = new Node("4");
        Node node4 = new Node("5");

        //insertamos en la izquierda habiendo ya un nodo en esta posición
        testTree.insert(node3.getContent(),testTree.getRoot(),true);
        Assertions.assertEquals("4", testTree.getRoot().getLeftChild().getContent().toString()); //Pasa el test

        //insertamos en la derecha habiendo ya un nodo en esta posición
        testTree.insert(node4.getContent(), testTree.getRoot(), false);
        Assertions.assertEquals("5", testTree.getRoot().getRightChild().getContent().toString()); //Pasa el test

        /*
        La función cumple su funcionalidad
         */
    }
}