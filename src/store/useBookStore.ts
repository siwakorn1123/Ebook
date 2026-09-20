import { create } from 'zustand';
import { Book, CONTINUE_READING_BOOK, BEST_SELLERS } from '../data/sampleBooks';

interface BookStore {
  userCoins: number;
  bookshelf: Book[];
  selectedCategory: string;
  activeReadingBook: Book;
  setSelectedCategory: (category: string) => void;
  addToShelf: (book: Book) => void;
  updateReadingProgress: (bookId: string, progress: number) => void;
  purchaseBook: (book: Book) => boolean;
}

export const useBookStore = create<BookStore>((set, get) => ({
  userCoins: 450,
  selectedCategory: "ทั้งหมด",
  activeReadingBook: CONTINUE_READING_BOOK,
  bookshelf: [
    CONTINUE_READING_BOOK,
    {
      ...BEST_SELLERS[0],
      isInShelf: true,
      status: 'UNREAD',
      shelfCategory: 'คัดสรรสำหรับทำงาน'
    }
  ],
  setSelectedCategory: (category) => set({ selectedCategory: category }),
  addToShelf: (book) => set((state) => ({
    bookshelf: [
      { ...book, isInShelf: true, status: 'UNREAD' },
      ...state.bookshelf.filter((b) => b.id !== book.id)
    ]
  })),
  updateReadingProgress: (bookId, progress) => set((state) => ({
    bookshelf: state.bookshelf.map((b) =>
      b.id === bookId
        ? {
            ...b,
            readProgress: progress,
            status: progress >= 100 ? 'FINISHED' : 'READING'
          }
        : b
    )
  })),
  purchaseBook: (book) => {
    const { userCoins, addToShelf } = get();
    if (userCoins >= book.price) {
      set({ userCoins: userCoins - book.price });
      addToShelf(book);
      return true;
    }
    return false;
  }
}));
