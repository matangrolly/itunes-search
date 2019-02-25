package au.com.appetiser.challenge.data.local.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import timber.log.Timber;

public class Track extends RealmObject {
  @PrimaryKey
  @SerializedName("trackId")
  private String trackId;
  @SerializedName("wrapperType")
  private String wrapperType;
  @SerializedName("kind")
  private String kind;
  @SerializedName("artistName")
  private String artistName;
  @SerializedName("trackName")
  private String trackName;
  @SerializedName("trackCensoredName")
  private String trackCensoredName;
  @SerializedName("trackViewUrl")
  private String trackViewUrl;
  @SerializedName("previewUrl")
  private String previewUrl;
  @SerializedName("artworkUrl30")
  private String artworkUrl30;
  @SerializedName("artworkUrl60")
  private String artworkUrl60;
  @SerializedName("artworkUrl100")
  private String artworkUrl100;
  @SerializedName("collectionPrice")
  private Double collectionPrice;
  @SerializedName("trackPrice")
  private Double trackPrice;
  @SerializedName("trackRentalPrice")
  private Double trackRentalPrice;
  @SerializedName("collectionHdPrice")
  private Double collectionHdPrice;
  @SerializedName("trackHdPrice")
  private Double trackHdPrice;
  @SerializedName("trackHdRentalPrice")
  private Double trackHdRentalPrice;
  @SerializedName("releaseDate")
  private String releaseDate;
  @SerializedName("collectionExplicitness")
  private String collectionExplicitness;
  @SerializedName("trackExplicitness")
  private String trackExplicitness;
  @SerializedName("trackTimeMillis")
  private Integer trackTimeMillis;
  @SerializedName("country")
  private String country;
  @SerializedName("currency")
  private String currency;
  @SerializedName("primaryGenreName")
  private String primaryGenreName;
  @SerializedName("contentAdvisoryRating")
  private String contentAdvisoryRating;
  @SerializedName("shortDescription")
  private String shortDescription;
  @SerializedName("longDescription")
  private String longDescription;
  @SerializedName("hasITunesExtras")
  private boolean hasITunesExtras;

  public String getTrackId() {
    return trackId;
  }

  public String getWrapperType() {
    return wrapperType;
  }

  public String getKind() {
    return kind;
  }

  public String getArtistName() {
    return artistName;
  }

  public String getTrackName() {
    return trackName;
  }

  public String getTrackCensoredName() {
    return trackCensoredName;
  }

  public String getTrackViewUrl() {
    return trackViewUrl;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public String getArtworkUrl30() {
    return artworkUrl30;
  }

  public String getArtworkUrl60() {
    return artworkUrl60;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public Double getCollectionPrice() {
    return collectionPrice;
  }

  public Double getTrackPrice() {
    return trackPrice;
  }

  public Double getTrackRentalPrice() {
    return trackRentalPrice;
  }

  public Double getCollectionHdPrice() {
    return collectionHdPrice;
  }

  public Double getTrackHdPrice() {
    return trackHdPrice;
  }

  public Double getTrackHdRentalPrice() {
    return trackHdRentalPrice;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getCollectionExplicitness() {
    return collectionExplicitness;
  }

  public String getTrackExplicitness() {
    return trackExplicitness;
  }

  public Integer getTrackTimeMillis() {
    return trackTimeMillis;
  }

  public String getCountry() {
    return country;
  }

  public String getCurrency() {
    return currency;
  }

  public String getPrimaryGenreName() {
    return primaryGenreName;
  }

  public String getContentAdvisoryRating() {
    return contentAdvisoryRating;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public boolean isHasITunesExtras() {
    return hasITunesExtras;
  }

  public String getArtworkUrlLarge() {
    try {
      return artworkUrl100.replace("100x100", "800x800");
    } catch (Exception e) {
      Timber.e(e);
    }

    return artworkUrl100;
  }
}