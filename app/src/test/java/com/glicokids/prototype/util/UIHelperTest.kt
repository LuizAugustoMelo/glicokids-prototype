package com.glicokids.prototype.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UIHelperTest {

    @Test
    fun `when glucose is inside target, should return NA_META`() {
        val result = UIHelper.glucoseStatus(120, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.NA_META)
    }

    @Test
    fun `when glucose is near lower edge, should return ATENCAO`() {
        val result = UIHelper.glucoseStatus(80, 70, 180) // 80 is 70 + 10 (within 15)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is near upper edge, should return ATENCAO`() {
        val result = UIHelper.glucoseStatus(170, 70, 180) // 170 is 180 - 10 (within 15)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is below target, should return FORA_DA_META`() {
        val result = UIHelper.glucoseStatus(60, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.FORA_DA_META)
    }

    @Test
    fun `when glucose is above target, should return FORA_DA_META`() {
        val result = UIHelper.glucoseStatus(200, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.FORA_DA_META)
    }

    @Test
    fun `when glucose is exactly at lower edge, should return ATENCAO`() {
        val result = UIHelper.glucoseStatus(70, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is exactly at upper edge, should return ATENCAO`() {
        val result = UIHelper.glucoseStatus(180, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is 15 mg-dL inside lower edge, should return ATENCAO`() {
        val result = UIHelper.glucoseStatus(85, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is 16 mg-dL inside lower edge, should return NA_META`() {
        val result = UIHelper.glucoseStatus(86, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.NA_META)
    }
}
