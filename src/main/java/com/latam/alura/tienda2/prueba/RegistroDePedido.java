package com.latam.alura.tienda2.prueba;

import com.latam.alura.tienda2.dao.CategoriaDao;
import com.latam.alura.tienda2.dao.ClienteDao;
import com.latam.alura.tienda2.dao.PedidoDao;
import com.latam.alura.tienda2.dao.ProductoDao;
import com.latam.alura.tienda2.modelo.*;
import com.latam.alura.tienda2.utils.JPAUtils;
import com.latam.alura.tienda2.vo.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedido {
    public static void main(String[] args) {

        //metodo abstracto
        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1L);


        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);



        Cliente cliente = new Cliente("Juan","k6757jb");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5,producto,pedido));

        em.getTransaction().begin();

        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);

        em.getTransaction().commit();

        BigDecimal valorTotal = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total: " + valorTotal);

        List<RelatorioDeVenta> relatorio = pedidoDao.relatorioDeVentasVO();

        relatorio.forEach(System.out::println);

    }






    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");

        Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"),celulares);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
