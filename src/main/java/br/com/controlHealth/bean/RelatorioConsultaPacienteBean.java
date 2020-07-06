package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.controlHealth.dao.JPAFactoryControlHealth;
import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.model.Paciente;
import br.com.controlHealth.model.RelatorioConsultaPacientePorAno;

@Named
@SessionScoped
public class RelatorioConsultaPacienteBean implements Serializable {

	private static final long serialVersionUID = 2520699327163576320L;

	private static final String RELATORIOS_CONSULTA_PACIENTE_POR_ANO = "/relatorios/relatorioConsultaPacientePorAno?faces-redirect=true";
	
	private BarChartModel barModel;
	
	@Inject
	private Paciente paciente;
	
	@Inject
	private PacienteDAOImpl daoPaciente;
	
	private List<Paciente> listaPacientes;

	public RelatorioConsultaPacienteBean() {
		barModel = new BarChartModel();
	}

	public void init() {
		System.out.println("Entrou no metodo init");
		createBarModel();
		System.out.println("Saiu do metodo init");
	}

	public void createBarModel() {
		barModel = initBarModel();
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Ano");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(30);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries pacienteChartSeries = null;
		List<RelatorioConsultaPacientePorAno> listaConsultaPaciente = null;
		List<ChartSeries> listChartSeries = new ArrayList<ChartSeries>();

		if (listaConsultaPaciente == null) {
			System.out.println("ID Paciente: " + paciente.getId());
			listaConsultaPaciente = getRelatorioConsultaPacienteById(paciente.getId());
			System.out.println("Consultas paciente: " + listaConsultaPaciente);

			for (RelatorioConsultaPacientePorAno paciente : listaConsultaPaciente) {
				pacienteChartSeries = new ChartSeries();
				pacienteChartSeries.setLabel(String.valueOf(paciente.getAno()));
				pacienteChartSeries.set("Quantidade", paciente.getQuantidade());
				listChartSeries.add(pacienteChartSeries);
			}

			for (ChartSeries chartSeries : listChartSeries) {
				model.addSeries(chartSeries);
			}
		}

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioConsultaPacientePorAno> getRelatorioConsultaPacienteById(Integer id) {
		EntityManager em = new JPAFactoryControlHealth().createEntityManager();
		Query query = em.createQuery(
				"select new br.com.controlHealth.model.RelatorioConsultaPacientePorAno (Year(c.dataConsulta), count(*)) from Consulta c"
						+ " where c.paciente.id = :pId"
						+ " group by Year(c.dataConsulta)");
		
		query.setParameter("pId", id);
		List<RelatorioConsultaPacientePorAno> listaRelatorio = query.getResultList();
		em.close();
		return listaRelatorio;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public String listaRelatorioConsultaPacientePorAno() {
		return RELATORIOS_CONSULTA_PACIENTE_POR_ANO;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getListaPacientes() {
		try {
			listaPacientes = this.daoPaciente.getAll();
		} catch (Exception e) {
			System.err.println("ConsultaBean - Erro ao recuperar lista de pacientes");
		}
		
		return listaPacientes;
	}

}
