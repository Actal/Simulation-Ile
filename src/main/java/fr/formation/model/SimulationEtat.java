package fr.formation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;

@Entity
@Table(name="sim_etat")
public class SimulationEtat {
    
    @Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SIM_ID")
    @JsonView(Views.Commons.class)
	private int id = 1;

    @Column(name = "SIM_TIME", nullable = false)
    @JsonView(Views.SimulationEtat.class)
	private LocalDateTime time;

    @Column(name = "SIM_PAUSE", nullable = false)
    @JsonView(Views.SimulationEtat.class)
	private boolean pause;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void add1Hour() {
        this.time.plusHours(1);
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

}
