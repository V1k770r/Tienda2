package com.latam.alura.tienda2.dao;

import com.latam.alura.tienda2.modelo.Pedido;
import com.latam.alura.tienda2.vo.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Pedido pedido){
        this.em.persist(pedido);
    }

    public Pedido consultaPorId(Long id){
        return em.find(Pedido.class, id);
    }

    public List<Pedido> consultarTodos(){
        String jqpl = "SELECT P FROM Producto AS P";
        return  em.createQuery(jqpl, Pedido.class).getResultList()    ;
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql,BigDecimal.class).getSingleResult();
    }

    public Double valorPromedioVendido(){
        String jpql = "SELECT AVG(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql,Double.class).getSingleResult();
    }

    public List<RelatorioDeVenta> relatorioDeVentasVO(){
        String jpql = "SELECT new com.latam.alura.tienda2.vo.RelatorioDeVenta(producto.nombre, "
                + "SUM(item.cantidad), "
                + "MAX(pedido.fecha)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.items item "
                + "JOIN item.producto producto "
                + "GROUP BY producto.nombre "
                + "ORDER BY item.cantidad DESC";
            return em.createQuery(jpql, RelatorioDeVenta.class).getResultList();

    }

}
