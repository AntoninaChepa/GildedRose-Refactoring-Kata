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
//    - Once the sell by date has passed, Quality degrades twice as fast
//	- "Aged Brie" actually increases in Quality the older it gets
//	- The Quality of an item is never more than 50
//            - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
//	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;

    @Test
    void controllaQualitaNonNegativa() {
        GildedRose app = appWithItem("genericItem", 10, 0);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }


    private GildedRose appWithItem(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        return new GildedRose(items);
    }
}
