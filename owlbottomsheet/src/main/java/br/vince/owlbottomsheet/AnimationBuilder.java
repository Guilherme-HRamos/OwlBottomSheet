package br.vince.owlbottomsheet;

import android.support.design.card.MaterialCardView;

/**
 * Classe criada por Guilherme Ramos em Agosto 2018
 * Contato: +55 (11) 974049050
 */
public class AnimationBuilder {

    private float toHeight;
    private float toWidth;
    private float fromHeight;
    private float fromWidth;
    private float fromX;
    private float fromY;
    private float toX;
    private float toY;
    private float fromRadius;
    private float toRadius;
    private MaterialCardView cardView;

    public float getToHeight() {
        return toHeight;
    }

    public void setToHeight(final float toHeight) {
        this.toHeight = toHeight;
    }

    public float getToWidth() {
        return toWidth;
    }

    public void setToWidth(final float toWidth) {
        this.toWidth = toWidth;
    }

    public float getFromHeight() {
        return fromHeight;
    }

    public void setFromHeight(final float fromHeight) {
        this.fromHeight = fromHeight;
    }

    public float getFromWidth() {
        return fromWidth;
    }

    public void setFromWidth(final float fromWidth) {
        this.fromWidth = fromWidth;
    }

    public float getFromX() {
        return fromX;
    }

    public void setFromX(final float fromX) {
        this.fromX = fromX;
    }

    public float getFromY() {
        return fromY;
    }

    public void setFromY(final float fromY) {
        this.fromY = fromY;
    }

    public float getToX() {
        return toX;
    }

    public void setToX(final float toX) {
        this.toX = toX;
    }

    public float getToY() {
        return toY;
    }

    public void setToY(final float toY) {
        this.toY = toY;
    }

    public float getFromRadius() {
        return fromRadius;
    }

    public void setFromRadius(final float fromRadius) {
        this.fromRadius = fromRadius;
    }

    public float getToRadius() {
        return toRadius;
    }

    public void setToRadius(final float toRadius) {
        this.toRadius = toRadius;
    }

    public MaterialCardView getCardView() {
        return cardView;
    }

    public void setCardView(final MaterialCardView cardView) {
        this.cardView = cardView;
    }
}
