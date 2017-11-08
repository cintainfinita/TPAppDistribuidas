package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.*;
import enumns.AreaRest;
import enumns.Estado;
import enumns.MedioDePago;
import hibernate.HibernateUtil;

public class testHibernate {

	//----------------------->Hagan sus pruebas relacionadas a HIBERNATE aqui <-----------------------------
	public static void main(String[] args) 
	{
		
		
		
		//aca van pruebas de DB
	/*	
		List<AreaRestaurantEntity> areas = new ArrayList<AreaRestaurantEntity>();
		AreaRest a = null;
		
		SalonEntity salon =  new SalonEntity(a.salon,"salon",sectores);	
		
		areas.add(salon);
		
		LocalEntity local = new LocalEntity("dir","barrio",areas);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(local);
		session.getTransaction().commit();
		session.close();
		*/

		SalonEntity salon=new SalonEntity(1,AreaRest.salon, "salon3");
		UnidadEntity ue = new UnidadEntity("gramos");
	
		SectorEntity sector = new SectorEntity("sectorcito", salon);
		List<SectorEntity> sectores = new ArrayList<SectorEntity>();
		sectores.add(sector);
		
		CajaEntity caja=new CajaEntity(2,AreaRest.Caja,salon);
		
		Estado est = null;
		PlanDeProduccionEntity pdpe = new PlanDeProduccionEntity(est.EnProceso);
		Date fecha = new Date("10/10/2020");
		
		MateriaPrimaEntity mpe = new MateriaPrimaEntity("desc",ue);
	
		List<MateriaPrimaEntity> materiales = new ArrayList<MateriaPrimaEntity>();
		materiales.add(mpe);
		SemiElaboradoEntity see = new SemiElaboradoEntity("tipo","calidad","desc",pdpe,1,fecha,materiales,ue);
		
		List<SemiElaboradoEntity> componentes = new ArrayList<SemiElaboradoEntity>();
		componentes.add(see);
		
		ElaboradoEntity ee = new ElaboradoEntity("tipo","calidad","pizza",pdpe,1,fecha, ue, componentes);
		
		List<ElaboradoEntity> elabs = new ArrayList<ElaboradoEntity>();
		elabs.add(ee);
		
		PlatoEntity plato = new PlatoEntity("platito",13f,elabs);
			
		MozoEntity mozo = new MozoEntity(31575032,"Nahuelito","Grisoluble",80.4f, sector);
		
		List<MozoEntity> mocitos = new ArrayList<MozoEntity>();
		mocitos.add(mozo);
	
		MesaEntity mesita = new MesaEntity(0,15,0,mozo,sector);
		
		List<MesaEntity> mesitas = new ArrayList<MesaEntity>();
		mesitas.add(mesita);
				
		
		ComandaEntity comandita = new ComandaEntity(mozo, mesita,caja);
		
		ItemComandaEntity itemCom= new ItemComandaEntity(2, plato, comandita);
		
		List<ComandaEntity> comanditas = new ArrayList<ComandaEntity>();
		comanditas.add(comandita);
		
		FacturaEntity factura= new FacturaEntity(fecha, 40.4f, MedioDePago.Contado, mesita, mozo,caja);
				
		ItemFacturaEntity itemfacturita = new ItemFacturaEntity(itemCom,factura);
		
	
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		session.save(itemfacturita);
		
		session.getTransaction().commit();
		session.close();
		
		
		//aca terminan pruebas de DB
		/*--------------->----------->separador de bajo presupuesto<------------------<------------------------*/				  
		  /*
 	public List<DTO> funcion() throws RemoteException {
		// TODO Auto-generated method stub
		return manejoNegocio.getInstance().getUnidades();
	}
 
 *****************
 *Mapeadas
 *sector
 *Mesa
 *Factura
 *itemFactura
 *Comanda
 *ItemComanda
 *cajas
 *arearestaurant
 *
 *
 *Hay que revisar varias cosas:
 *Comanda es un OneToMany pero lo mapeao como un one to one con item comanda, no se por que
 *revisar mesa factura (tiene que ser 1:1) y no sale asi
 *item facutra item comanda (para mi es 1:1 pero vos pusiste otra cosa en el codigo)
 *e item comanda con plato que tambien es 1:1 pero no lo dibuja asi
 *hay algo rarisimo en arearestaurant pero ma;ana lo veo
 
 
 */
/*--------------->----------->separador de bajo presupuesto<------------------<------------------------*/		
	/*ABAJO SCRIPT PARA BORRAR TABLAS EN BASE DE DATOS,LO CORRES APUNTANDO A LA BASE Y LISTO*/	
		
		/*
		 * 
		 * 

 DECLARE @Sql NVARCHAR(500) DECLARE @Cursor CURSOR

SET @Cursor = CURSOR FAST_FORWARD FOR
SELECT DISTINCT sql = 'ALTER TABLE [' + tc2.TABLE_NAME + '] DROP [' + rc1.CONSTRAINT_NAME + ']'
FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS rc1
LEFT JOIN INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc2 ON tc2.CONSTRAINT_NAME =rc1.CONSTRAINT_NAME

OPEN @Cursor FETCH NEXT FROM @Cursor INTO @Sql

WHILE (@@FETCH_STATUS = 0)
BEGIN
Exec sp_executesql @Sql
FETCH NEXT FROM @Cursor INTO @Sql
END

CLOSE @Cursor DEALLOCATE @Cursor
GO

EXEC sp_MSforeachtable 'DROP TABLE ?'
GO
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		/*--------------->----------->separador de bajo presupuesto<------------------<------------------------*/				
	}

}
