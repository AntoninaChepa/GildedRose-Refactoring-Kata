package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void degradaQualitaESellinOgniGiorno() {
        GildedRose app = appWithItem("genericItem", 10, 9);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

//    All items have a SellIn value which denotes the number of days we have to sell the item
//	- All items have a Quality value which denotes how valuable the item is
//	- "Aged Brie" actually increases in Quality the older it gets
//	- The Quality of an item is never more than 50
//            - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
//	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
//Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
//    Quality drops to 0 after the concert


    @Test
    void controllaQualitaNonNegativa() {
        GildedRose app = appWithItem("genericItem", 10, 0);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void quandoDataDiVenditaScadutaLaQualitaDegradaAlDoppioDellaVelocita() {
        GildedRose app = appWithItem("genericItem", 0, 8);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void agedBrie_AumentaQualitaInveceDiDiminuire() {
        GildedRose app = appWithItem("Aged Brie", 8, 10);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void agedBrie_NonVaMaiOltreQualita50() {
        GildedRose app = appWithItem("Aged Brie", 8, 50);
        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfuras_NonDegradaMaiLaQualita() {
        GildedRose app = appWithItem("Sulfuras, Hand of Ragnaros", 10, 80);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }


    @Test
    void backstage_la_qualita_non_supera_50_con_data_scadenza_minore_di_11() {
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_la_qualita_non_supera_50_con_data_scadenza_minore_di_5() {
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_la_qualita_aumenta_di_1_se_giorni_piu_di_10() {
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void backstage_la_qualita_aumenta_di_2_se_giorni_10() {
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void backstage_la_qualita_aumenta_di_3_se_giorni_meno_di_5() {
        int quality = 20;
        int sellIn = 5;
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
        app.updateQuality();
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 3, app.items[0].quality);
    }

    @Test
    void backstage_crolla_la_qualita_a_0_se_data_scadenza_superata() {
        GildedRose app = appWithItem("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }


    private GildedRose appWithItem(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        return new GildedRose(items);
    }
}


