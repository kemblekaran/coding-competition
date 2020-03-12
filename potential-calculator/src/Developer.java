import java.util.Set;

public class Developer {
    private String company;
    private Integer bonus;
    private Set skillset;

    public Developer(String company, Integer bonus, Set skillset) {
        this.company = company;
        this.bonus = bonus;
        this.skillset = skillset;
    }

    public String getCompany() {
        return company;
    }

    public Integer getBonus() {
        return bonus;
    }

    public Set getSkillset() {
        return skillset;
    }
}
