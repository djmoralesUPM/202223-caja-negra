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
import java.util.Iterator;
import org.junit.jupiter.api.function.Executable;

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
    @Test
    public void searchTest() {

        /*
        Clases aceptables -> Contenido que existe en en árbol
        Clases no aceptables -> Contenido que no existe en el árbol
         */

        BinaryTree testTree = new BinaryTree("1");

        //Se busca un contenido que exista en el árbol
        Assertions.assertEquals("1", testTree.search("1").getContent().toString()); // Pasa el test

        //Se busca un contenido que no exista en el árbol
        Assertions.assertNull(testTree.search("2")); // Pasa el test
    }

    @Test
    public void removeTest() {

        /*
        Clases aceptables -> Borrar contenido que existe en el árbol
        Clases no aceptables -> Borrar contenido que no existe en el árbol
         */

        BinaryTree testTree = new BinaryTree("1");
        Node node1 = new Node("2");

        // Insertamos el nodo en el árbol
        testTree.insert(node1.getContent(), testTree.getRoot(), true);
        Assertions.assertNotNull(testTree.search("2")); //Pasa el test

        //Borramos el nodo del árbol
        testTree.remove(node1);
        Assertions.assertNull(testTree.search("2")); //No pasa el test

        /*
        Esta función no cumple su funcionalidad
         */
    }

    @Test
    public void equalsTest() {

        /*
        Clases aceptables -> Comparar árbol con árbol
        Clases no aceptables -> Comparar árbol con valor nulo
         */

        BinaryTree testTree1 = new BinaryTree("1");
        Node node1 = new Node("2");

        //Comparamos el árbol a uno nulo
        //Assertions.assertFalse(testTree1.equals(null)); //no pasa el test ya que no acepta valor nulo

        //Creamos un nuevo árbol e insertamos el node1 al primer árbol para comparar árboles diferentes
        testTree1.insert(node1.getContent(), testTree1.getRoot(),false);
        BinaryTree testTree2 = new BinaryTree("1");
        //Assertions.assertFalse(testTree1.equals(testTree2)); //no pasa el test

        //Añadimos el nodo al segundo árbol para que sean iguales
        testTree2.insert(node1.getContent(), testTree2.getRoot(), false);
        Assertions.assertTrue(testTree1.equals(testTree2)); //Pasa el test

        /*
        La función no cumple su funcionalidad
         */
    }

    @Test
    public void toListTest() {

        /*
        Clases aceptables -> árbol de uno solo nodo,
                             árbol que acaba en un nodo izquierdo,
                             árbol que acaba en un nodo derecho,
                             árbol que acaba con nodos en ambos lados
        Clases no aceptables -> árbol nulo (no es comprobable ya que no se puede instanciar un árbol sin nodos)
         */

        BinaryTree testTree = new BinaryTree("1");
        Node node1 = new Node("2");
        Node node2 = new Node("3");

        Assertions.assertEquals("[1]",testTree.toList().toString()); // Pasa el test

        //Insertamos un nodo a la izqquierda
        testTree.insert(node1.getContent(),testTree.getRoot(),true);
        Assertions.assertEquals("[1, 2]",testTree.toList().toString()); // Pasa el test

        //Creamos un nuevo árbol e insertamos el nodo 2 a la derecha
        BinaryTree testTree2 = new BinaryTree("1");
        testTree2.insert(node2.getContent(), testTree2.getRoot(), false);
        Assertions.assertEquals("[1, 3]", testTree2.toList().toString()); // Pasa el test

        //Insertamos el nodo 2 al primer árbol
        testTree.insert(node2.getContent(),testTree.getRoot(), false);
        Assertions.assertEquals("[1, 2, 3]", testTree.toList().toString()); // Pasa el test

        /*
        La funcion cumple su funcionalidad
         */
    }

    @Test
    public void getSubTreeTest() {

        /*
        Clases aceptables -> árbol con un nodo,
                             árbol que acaba en un nodo izquierdo,
                             árbol que acaba en un nodo derecho,
                             árbol que acaba en nodo en ambas posiciones.
        Clases no aceptables -> valor nulo
         */

        BinaryTree testTree1 = new BinaryTree("1");
        Node node1 = new Node("2");
        Node node2 = new Node("3");

        //Probamos creando un subárbol desde la raiz
        BinaryTree subTree1 = testTree1.getSubTree(testTree1.getRoot());
        Assertions.assertEquals("1",subTree1.getRoot().getContent().toString()); // Pasa el test

        //Probamos a añadir un nodo a la izquierda y crear un subárbol desde dicho nodo
        testTree1.insert(node1.getContent(), testTree1.getRoot(), true);
        Assertions.assertEquals("2",testTree1.getSubTree(node1).getRoot().getContent().toString()); // Pasa el test

        //Creamos otro árbol y añadimos el nodo a la derecha y creamos un subárbol desde dicho nodo
        BinaryTree testTree2 = new BinaryTree("1");
        testTree2.insert(node2.getContent(),testTree2.getRoot(),false);
        Assertions.assertEquals("3",testTree2.getSubTree(node2).getRoot().getContent().toString()); // Pasa el test

        //Añadimos otro nodo a la izquierda y creamos un subárbol que acaba en derecha e izquierda
        testTree2.insert(node1.getContent(),testTree2.getRoot(),true);
        Assertions.assertEquals("[1, 2, 3]", testTree2.getSubTree(testTree2.getRoot()).toList().toString()); // Pasa el test

        /*
        Esta función cumple su funcionalidad
         */

    }

    @Test
    public void depthTest() {

        /*
        Clases aceptables -> Árbol con un solo nodo,
                             árbol con dos o más nodos,
                             desde un nodo que se encuentra en el árbol.
        Clases no aceptables -> Desde nodo que no se encuentra en el árbol.
         */

        BinaryTree testTree = new BinaryTree("1");

        //Probamos con un árbol de un solo nodo
        Assertions.assertEquals(1,testTree.depth()); // No pasa el test

        //Insertamos Nodos
        Node node1 = testTree.insert("2",testTree.getRoot(),false);
        Node node2 = testTree.insert("3",testTree.getRoot(),true);
        testTree.insert("4",node1,false);
        testTree.insert("5",node1,true);
        testTree.insert("6",node2,false);
        testTree.insert("7",node2,true);

        //Comprobamos la profundidad desde la raiz
        Assertions.assertEquals(3,testTree.depth()); //No pasa el test

        //Comprobamos la profundidad desde un nodo intermedio
        Assertions.assertEquals(2,testTree.depth(testTree.search(node1.getContent()))); //No pasa el test

        //Comprobamos la profundiad desde un nodo que no existe
        Node node3 = new Node("8");
        Assertions.assertNull(testTree.getSubTree(node3)); //Produce una excepción

        /*
        Esta función no cumple con su funcionalidad
         */

    }
}