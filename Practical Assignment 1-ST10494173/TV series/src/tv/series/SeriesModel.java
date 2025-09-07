/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tv.series;

/**
 *
 * @author PABALELO
 */
public class SeriesModel {
    private String seriesId;
    private String seriesName;
    private int seriesAge;
    private int seriesNumberOfEpisodes;

    // Constructor with int parameters
    public SeriesModel(String seriesId, String seriesName, int seriesAge) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.seriesAge = seriesAge;
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    // Constructor with String parameters that parses age and episodes
    public SeriesModel(String seriesId, String seriesName, String seriesAge, String seriesNumberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.seriesAge = Integer.parseInt(seriesAge);
        this.seriesNumberOfEpisodes = Integer.parseInt(seriesNumberOfEpisodes);
    }

    // Getters and setters
    public String getSeriesId() { return seriesId; }
    public void setSeriesId(String seriesId) { this.seriesId = seriesId; }

    public String getSeriesName() { return seriesName; }
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }

    public int getSeriesAge() { return seriesAge; }
    public void setSeriesAge(int seriesAge) { this.seriesAge = seriesAge; }

    public int getSeriesNumberOfEpisodes() { return seriesNumberOfEpisodes; }
    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) { this.seriesNumberOfEpisodes = seriesNumberOfEpisodes; }

    @Override
    public String toString() {
        return "ID: " + seriesId + ", Name: " + seriesName + ", Age Restriction: " + seriesAge + ", Episodes: " + seriesNumberOfEpisodes;
    }
}
