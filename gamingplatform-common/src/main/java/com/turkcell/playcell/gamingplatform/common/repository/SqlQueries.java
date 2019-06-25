package com.turkcell.playcell.gamingplatform.common.repository;

public class SqlQueries {
    public final static String FIND_GAME_BY_PLATFORM_NAME_AND_LANGUAGE = "SELECT g FROM Game g JOIN GameDetail gd ON gd.game=g.id " +
            "JOIN Platform p ON p.id=gd.platform JOIN GameDetailTranslation gdt ON gdt.gameDetail=gd.id JOIN Language lang ON lang.id=gdt.language " +
            "WHERE upper(p.name)=upper(:platformName) AND upper(lang.shortName)=upper(:language) AND gd.isActive=true " +
            "AND gd.publishDatetime <=:datetime AND (gd.unpublishDatetime > :datetime OR gd.unpublishDatetime is null) ORDER BY g.score";
    public final static String FIND_TOTAL_GAME_NUMBER = "SELECT COUNT(gd) FROM Game_Detail gd JOIN Game g ON g.id=gd.game_id JOIN Platform p ON p.id=gd.platform_id JOIN Game_Detail_Tariff gdt ON gdt.game_detail_id=gd.id JOIN Tariff t ON gdt.tariff_id=t.id";
    public final static String FIND_GAME_NUMBER_BY_TARIFF = "SELECT COUNT(DISTINCT gd.id) FROM Game_Detail gd JOIN Game g ON g.id=gd.game_id JOIN Platform p ON p.id=gd.platform_id JOIN Game_Detail_Tariff gdt ON gdt.game_detail_id=gd.id JOIN Tariff t ON gdt.tariff_id=t.id WHERE t.name=?1 and p.name=?2";
    public final static String FIND_TOTAL_CATEGORY_NUMBER = "SELECT COUNT(DISTINCT c.id) FROM Category c JOIN Game_Detail_Category gdc ON c.id=gdc.category_id WHERE c.is_tag=false";//0 in oracle, false in postgres
    public final static String FIND_GAME_BY_PLATFORM_ID_AND_TARIFF_ID = "SELECT g FROM Game g JOIN GameDetail gd ON gd.game=g.id JOIN Platform p ON p.id=gd.platform " +
            "JOIN gd.tariffs t WHERE p.id=:platformId and t.id=:tariffId";
    public final static String FIND_LATEST_GAME_SLUG = "SELECT gs.url FROM Game_Slug gs WHERE gs.game_detail_translation_id=?1 ORDER BY gs.create_date DESC LIMIT 1 ";
    public final static String FIND_GAME_URL = "SELECT g FROM Game g INNER JOIN g.gameDetails gd INNER JOIN gd.gameDetailTranslations gdt INNER JOIN gdt.gameSlugs gs " +
            "WHERE lower(gd.platform.name) = lower(:platformName) AND lower(gdt.language.shortName) = lower(:languageName) AND lower(gs.url) = lower(:url)";
}
