package framework.core.domain.navigation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;

@Entity
@Table(name = "NAVIGATION")
public class Navigation extends BaseEntity {

    private static final long serialVersionUID = -2779742676392793907L;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Navigation> childs;

    @Column
    private String display;

    @ManyToOne(fetch = FetchType.EAGER)
    private Navigation parent;

    @Column
    private String url;

    public List<Navigation> getChilds() {
        return this.childs;
    }

    public String getDisplay() {
        return this.display;
    }

    public Navigation getParent() {
        return this.parent;
    }

    public String getUrl() {
        return this.url;
    }

    public void setChilds(List<Navigation> childs) {
        this.childs = childs;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setParent(Navigation parent) {
        this.parent = parent;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
