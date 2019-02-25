package au.com.appetiser.challenge.data.local.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import timber.log.Timber;

public class Track extends RealmObject {
  @SerializedName("artistName")
  private String artistName;

  @SerializedName("artworkUrl100")
  private String artworkUrl100;

  @SerializedName("artworkUrl30")
  private String artworkUrl30;

  @SerializedName("artworkUrl60")
  private String artworkUrl60;

  @SerializedName("collectionExplicitness")
  private String collectionExplicitness;

  @SerializedName("collectionHdPrice")
  private Double collectionHdPrice;

  @SerializedName("collectionPrice")
  private Double collectionPrice;

  @SerializedName("contentAdvisoryRating")
  private String contentAdvisoryRating;

  @SerializedName("country")
  private String country;

  @SerializedName("currency")
  private String currency;

  @SerializedName("hasITunesExtras")
  private boolean hasITunesExtras;

  @SerializedName("kind")
  private String kind;

  @SerializedName("longDescription")
  private String longDescription;

  @SerializedName("previewUrl")
  private String previewUrl;

  @SerializedName("primaryGenreName")
  private String primaryGenreName;

  @SerializedName("releaseDate")
  private String releaseDate;

  private String searchTerm;

  @SerializedName("shortDescription")
  private String shortDescription;

  @SerializedName("trackCensoredName")
  private String trackCensoredName;

  @SerializedName("trackExplicitness")
  private String trackExplicitness;

  @SerializedName("trackHdPrice")
  private Double trackHdPrice;

  @SerializedName("trackHdRentalPrice")
  private Double trackHdRentalPrice;

  @PrimaryKey
  @SerializedName("trackId")
  private String trackId;

  @SerializedName("trackName")
  private String trackName;

  @SerializedName("trackPrice")
  private Double trackPrice;

  @SerializedName("trackRentalPrice")
  private Double trackRentalPrice;

  @SerializedName("trackTimeMillis")
  private Integer trackTimeMillis;

  @SerializedName("trackViewUrl")
  private String trackViewUrl;

  @SerializedName("wrapperType")
  private String wrapperType;

  public String getArtistName() {
    return artistName;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public String getArtworkUrl30() {
    return artworkUrl30;
  }

  public String getArtworkUrl60() {
    return artworkUrl60;
  }

  public String getArtworkUrlLarge() {
    try {
      return artworkUrl100.replace("100x100", "800x800");
    } catch (Exception e) {
      Timber.e(e);
    }

    return artworkUrl100;
  }

  public String getCollectionExplicitness() {
    return collectionExplicitness;
  }

  public Double getCollectionHdPrice() {
    return collectionHdPrice;
  }

  public Double getCollectionPrice() {
    return collectionPrice;
  }

  public String getContentAdvisoryRating() {
    return contentAdvisoryRating;
  }

  public String getCountry() {
    return country;
  }

  public String getCurrency() {
    return currency;
  }

  public String getKind() {
    return kind;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public String getPrimaryGenreName() {
    return primaryGenreName;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(final String searchTerm) {
    this.searchTerm = searchTerm;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getTrackCensoredName() {
    return trackCensoredName;
  }

  public String getTrackExplicitness() {
    return trackExplicitness;
  }

  public Double getTrackHdPrice() {
    return trackHdPrice;
  }

  public Double getTrackHdRentalPrice() {
    return trackHdRentalPrice;
  }

  public String getTrackId() {
    return trackId;
  }

  public String getTrackName() {
    return trackName;
  }

  public Double getTrackPrice() {
    return trackPrice;
  }

  public Double getTrackRentalPrice() {
    return trackRentalPrice;
  }

  public Integer getTrackTimeMillis() {
    return trackTimeMillis;
  }

  public String getTrackViewUrl() {
    return trackViewUrl;
  }

  public String getWrapperType() {
    return wrapperType;
  }

  public boolean isHasITunesExtras() {
    return hasITunesExtras;
  }
}