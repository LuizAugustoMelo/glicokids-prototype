package com.glicokids.prototype.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UIHelperTest {

    @Test
    fun `when glucose is inside target, should return NA_META`() {
        val result = UIHelper.getGlucoseStatus(120, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.NA_META)
    }

    @Test
    fun `when glucose is near lower edge, should return ATENCAO`() {
        val result = UIHelper.getGlucoseStatus(80, 70, 180) // 80 is 70 + 10 (within 15)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is near upper edge, should return ATENCAO`() {
        val result = UIHelper.getGlucoseStatus(170, 70, 180) // 170 is 180 - 10 (within 15)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.ATENCAO)
    }

    @Test
    fun `when glucose is below target, should return FORA_DA_META`() {
        val result = UIHelper.getGlucoseStatus(60, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.FORA_DA_META)
    }

    @Test
    fun `when glucose is above target, should return FORA_DA_META`() {
        val result = UIHelper.getGlucoseStatus(200, 70, 180)
        assertThat(result).isEqualTo(UIHelper.GlucoseStatus.FORA_DA_META)
    }
}
