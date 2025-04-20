package dto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author kensyu
 * TODO DTOは分割するか要検討（テーブルが3つあるので、3つのDTO使うべき）
 * TODO DTOも分割した方がいいと思っている PtcgCardDto PtcgCategoryDto PtcgExpansionDto
 * @param cardId カードID
 * @param cardName カード名
 * @param categoryId カテゴリID
 * @param categoryName カテゴリ名 (本来別テーブルにある情報)
 * @param expansionCode エキスパンションコード (本来別テーブルにある情報)
 * @param expansionName 収録元商品名称 (本来別テーブルにある情報)
 * @param releaseDate 収録元商品発売日 (本来別テーブルにある情報)
 * @param imageFileName 画像名
 * @param registrationTime 登録日
 * @param updateTime 更新日
 * @param price 価格
 */
public class PtcgDto {
	public PtcgDto() {
	}

	/**
	 * 全件取得用のコンストラクタ
	 * @param cardId
	 * @param cardName
	 * @param categoryName
	 * @param expansionName
	 * @param imageFileName
	 * @param releaseDate
	 * @param registrationTime
	 * @param updateTime
	 * @param price
	 */
	public PtcgDto(Integer cardId, String cardName, String categoryName,
			String expansionName, String imageFileName, Date releaseDate, Timestamp registrationTime, Timestamp updateTime,
			Integer price) {
		this.cardId = cardId;
		this.cardName = cardName;
		this.categoryName = categoryName;
		this.expansionName = expansionName;
		this.imageFileName = imageFileName;
		this.releaseDate = releaseDate;
		this.registrationTime = registrationTime;
		this.updateTime = updateTime;
		this.price = price;
	}

	public PtcgDto(Integer cardId, String cardName, Integer categoryId, String categoryName, String expansionCode,
			String expansionName, String imageFileName, Date releaseDate, Timestamp registrationTime, Timestamp updateTime,
			Integer price) {
		this.cardId = cardId;
		this.cardName = cardName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.expansionCode = expansionCode;
		this.expansionName = expansionName;
		this.imageFileName = imageFileName;
		this.releaseDate = releaseDate;
		this.registrationTime = registrationTime;
		this.updateTime = updateTime;
		this.price = price;
	}

	/**
	 * カテゴリテーブル取得用のコンストラクタ
	 * @param categoryId
	 * @param categoryName
	 */
	public PtcgDto(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	/**
	 * エキスパンションテーブル取得用のコンストラクタ
	 * 引数の型が違うからたまたま別のコンストラクタにできただけ
	 * TODO 本来は、DTO自体切り離したい
	 * @param expansionCode
	 * @param expansionName
	 * @param date 
	 */
	public PtcgDto(String expansionCode, String expansionName, Date releaseDate) {
		this.expansionCode = expansionCode;
		this.expansionName = expansionName;
		this.releaseDate = releaseDate;
	}

	/**
	 * @param cardId
	 * @param cardName
	 * @param categoryId
	 * @param expansionCode
	 * @param price
	 */
	public PtcgDto(Integer cardId, String cardName, Integer categoryId, String expansionCode, Integer price) {
		this.cardId = cardId;
		this.cardName = cardName;
		this.categoryId = categoryId;
		this.expansionCode = expansionCode;
		this.price = price;
	}

	private Integer cardId;
	private String cardName;
	private Integer categoryId;
	private String categoryName;
	private String expansionCode;
	private String expansionName;
	private String imageFileName;
	private Date releaseDate;
	private Timestamp registrationTime;
	private Timestamp updateTime;
	private Integer price;

	//	以下getter setter

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getExpansionCode() {
		return expansionCode;
	}

	public void setExpansionCode(String expansionCode) {
		this.expansionCode = expansionCode;
	}

	public String getExpansionName() {
		return expansionName;
	}

	public void setExpansionName(String expansionName) {
		this.expansionName = expansionName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Timestamp getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}