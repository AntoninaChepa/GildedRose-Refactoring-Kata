package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

//    All items have a SellIn value which denotes the number of days we have to sell the item
//	- All items have a Quality value which denotes how valuable the item is
//	- At the end of each day our system lowers both values for every item
    @Test
    void degradaQualitaESellinOgniGiorno() {
        Item[] items = new Item[]{new Item("genericItem", 10, 9)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }
//    - Once the sell by date has passed, Quality degrades twice as fast
//	- The Quality of an item is never negative
//	- "Aged Brie" actually increases in Quality the older it gets
//	- The Quality of an item is never more than 50
//            - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
//	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;


}
