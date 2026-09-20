import React, { useState } from 'react';
import {
  View,
  Text,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  Dimensions
} from 'react-native';
import { useLocalSearchParams, router } from 'expo-router';
import { MaterialIcons, Feather } from '@expo/vector-icons';
import { useBookStore } from '../../store/useBookStore';
import { CONTINUE_READING_BOOK, BEST_SELLERS } from '../../data/sampleBooks';

type ThemeMode = 'CREAM' | 'WHITE' | 'DARK';

export default function ReaderScreen() {
  const { id } = useLocalSearchParams();
  const { bookshelf, updateReadingProgress } = useBookStore();

  const currentBook =
    bookshelf.find((b) => b.id === id) ||
    (id === CONTINUE_READING_BOOK.id ? CONTINUE_READING_BOOK : BEST_SELLERS[0]);

  const [fontSize, setFontSize] = useState(16);
  const [themeMode, setThemeMode] = useState<ThemeMode>('CREAM');
  const [progress, setProgress] = useState(currentBook.readProgress || 20);

  const getThemeColors = () => {
    switch (themeMode) {
      case 'WHITE':
        return { bg: '#FFFFFF', text: '#1A202C', sub: '#718096' };
      case 'DARK':
        return { bg: '#1A202C', text: '#E2E8F0', sub: '#A0AEC0' };
      case 'CREAM':
      default:
        return { bg: '#FBF8F1', text: '#2D3748', sub: '#718096' };
    }
  };

  const colors = getThemeColors();

  return (
    <View style={[styles.container, { backgroundColor: colors.bg }]}>
      {/* Top Bar */}
      <View style={[styles.topBar, { backgroundColor: colors.bg }]}>
        <TouchableOpacity
          style={styles.iconBtn}
          onPress={() => router.back()}
        >
          <MaterialIcons name="arrow-back" size={24} color={colors.text} />
        </TouchableOpacity>
        <View style={styles.topInfo}>
          <Text style={[styles.bookTitle, { color: colors.text }]} numberOfLines={1}>
            {currentBook.title}
          </Text>
          <Text style={[styles.chapterTitle, { color: colors.sub }]} numberOfLines={1}>
            บทที่ 1 • ไม่มีใครบ้า
          </Text>
        </View>
        <TouchableOpacity style={styles.iconBtn}>
          <Feather name="bookmark" size={22} color={colors.text} />
        </TouchableOpacity>
      </View>

      {/* Reader Body */}
      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.textContainer}
      >
        <Text style={[styles.chapterHeading, { color: colors.text }]}>
          บทที่ 1: ไม่มีใครบ้า
        </Text>

        <Text style={[styles.paragraph, { color: colors.text, fontSize, lineHeight: fontSize * 1.6 }]}>
          ประสบการณ์ของคุณกับเงินอาจคิดเป็นเพียง 0.00000001% ของสิ่งที่เกิดขึ้นจริงทั้งหมดในโลก แต่กลับคิดเป็นถึง 80% ของวิธีที่คุณคิดว่าโลกใบนี้ทำงาน
        </Text>

        <Text style={[styles.paragraph, { color: colors.text, fontSize, lineHeight: fontSize * 1.6 }]}>
          คนที่มีภูมิหลังต่างกัน เติบโตมาในช่วงเศรษฐกิจที่แตกต่างกัน ย่อมมองความเสี่ยงและผลตอบแทนในมิติที่ไม่เหมือนกัน คนที่ผ่านพ้นภาวะเงินเฟ้อรุนแรงในวัยหนุ่มสาว จะมองตลาดหุ้นและการลงทุนด้วยสายตาที่คนยุคเติบโตเฟื่องฟูไม่มีวันเข้าใจ
        </Text>

        <Text style={[styles.paragraph, { color: colors.text, fontSize, lineHeight: fontSize * 1.6 }]}>
          ดังนั้น อย่าได้ตัดสินการตัดสินใจทางการเงินของผู้อื่นว่า "บ้าบอ" เพราะเมื่อคุณนำเอาเรื่องราวในชีวิต ประสบการณ์ และความกลัวของเขาเข้ามาเป็นตัวแปร การกระทำเหล่านั้นมักจะมีเหตุผลและสมเหตุสมผลสำหรับเขาเสมอ ณ เวลานั้น
        </Text>
      </ScrollView>

      {/* Bottom Floating Control Bar */}
      <View style={styles.controlsDock}>
        {/* Font size adjuster */}
        <View style={styles.controlRow}>
          <TouchableOpacity
            onPress={() => setFontSize(Math.max(13, fontSize - 1))}
            style={styles.adjustBtn}
          >
            <Text style={styles.adjustText}>A-</Text>
          </TouchableOpacity>
          <Text style={styles.sizeIndicator}>{fontSize}pt</Text>
          <TouchableOpacity
            onPress={() => setFontSize(Math.min(24, fontSize + 1))}
            style={styles.adjustBtn}
          >
            <Text style={styles.adjustText}>A+</Text>
          </TouchableOpacity>
        </View>

        {/* Theme Toggles */}
        <View style={styles.themeRow}>
          <TouchableOpacity
            onPress={() => setThemeMode('CREAM')}
            style={[
              styles.themeCircle,
              { backgroundColor: '#FBF8F1' },
              themeMode === 'CREAM' && styles.themeSelected
            ]}
          />
          <TouchableOpacity
            onPress={() => setThemeMode('WHITE')}
            style={[
              styles.themeCircle,
              { backgroundColor: '#FFFFFF' },
              themeMode === 'WHITE' && styles.themeSelected
            ]}
          />
          <TouchableOpacity
            onPress={() => setThemeMode('DARK')}
            style={[
              styles.themeCircle,
              { backgroundColor: '#1A202C' },
              themeMode === 'DARK' && styles.themeSelected
            ]}
          />
        </View>

        {/* Progress Display */}
        <View style={styles.progressRow}>
          <Text style={styles.dockProgressText}>{progress}%</Text>
          <TouchableOpacity
            style={styles.nextPageBtn}
            onPress={() => {
              const next = Math.min(100, progress + 5);
              setProgress(next);
              updateReadingProgress(currentBook.id, next);
            }}
          >
            <Text style={styles.nextPageText}>หน้าถัดไป</Text>
            <MaterialIcons name="chevron-right" size={18} color="#FFFFFF" />
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
  topBar: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: 16,
    paddingTop: 54,
    paddingBottom: 10,
    borderBottomWidth: 0.5,
    borderBottomColor: 'rgba(0,0,0,0.05)'
  },
  topInfo: {
    flex: 1,
    marginHorizontal: 12,
    alignItems: 'center'
  },
  bookTitle: {
    fontSize: 14,
    fontWeight: '700'
  },
  chapterTitle: {
    fontSize: 11,
    marginTop: 2
  },
  iconBtn: {
    padding: 6
  },
  textContainer: {
    paddingHorizontal: 24,
    paddingVertical: 24,
    paddingBottom: 120
  },
  chapterHeading: {
    fontSize: 20,
    fontWeight: '700',
    marginBottom: 20
  },
  paragraph: {
    marginBottom: 18,
    textAlign: 'justify'
  },
  controlsDock: {
    position: 'absolute',
    bottom: 24,
    left: 20,
    right: 20,
    backgroundColor: '#FFFFFF',
    borderRadius: 20,
    paddingHorizontal: 18,
    paddingVertical: 12,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 10,
    elevation: 8
  },
  controlRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8
  },
  adjustBtn: {
    paddingHorizontal: 8,
    paddingVertical: 4,
    backgroundColor: '#F7FAFC',
    borderRadius: 6
  },
  adjustText: {
    fontSize: 13,
    fontWeight: '700',
    color: '#2D3748'
  },
  sizeIndicator: {
    fontSize: 12,
    color: '#718096'
  },
  themeRow: {
    flexDirection: 'row',
    gap: 8
  },
  themeCircle: {
    width: 22,
    height: 22,
    borderRadius: 11,
    borderWidth: 1.5,
    borderColor: '#E2E8F0'
  },
  themeSelected: {
    borderColor: '#C85A32',
    borderWidth: 2
  },
  progressRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8
  },
  dockProgressText: {
    fontSize: 12,
    fontWeight: '700',
    color: '#C85A32'
  },
  nextPageBtn: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#C85A32',
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 12
  },
  nextPageText: {
    color: '#FFFFFF',
    fontSize: 11,
    fontWeight: '600'
  }
});
