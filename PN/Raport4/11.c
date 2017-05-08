#include <stdio.h>
#include <stdlib.h>

int main(){}

void byterun(signed char a[], int l)
{
    int i = 0;

    while (i < l)
    {
        if ((i < l-1) && (a[i] == a[i+1]))
        {
            int j = 0;
            while ((i+j < l-1) && (a[i+j] == a[i+j+1]) && (j < 127)) j++;
            printf("%d, %d, ", -j, (int)a[i+j]);
            i += (j+1);
        }
        else
        {
            int j=0;
            while ((i+j < l-1) && (a[i+j] != a[j+i+1]) && (j < 128)) j++;
            if ((i+j == l-1) && (j < 128)) j++;
            printf("%d, ", (j-1));
            for (int k=0; k<j; k++)
                printf("%d, ",(int)a[i+k]);
            i += j;
        }
    }
}

//Nie potrafilem zrobić Hufmanna i LZW: na podstawie Rosettacode
 
struct huffcode {
    int nbits;
    int code;
};
typedef struct huffcode huffcode_t;
 
struct huffheap {
    int *h;
    int n, s, cs;
    long *f;
};
typedef struct huffheap heap_t;
 
static heap_t *_heap_create(int s, long *f)
{
    heap_t *h;
    h = malloc(sizeof(heap_t));
    h->h = malloc(sizeof(int)*s);
    h->s = h->cs = s;
    h->n = 0;
    h->f = f;
    return h;
}
 
static void _heap_destroy(heap_t *heap)
{
    free(heap->h);
    free(heap);
}
 
static void _heap_sort(heap_t *heap)
{
    int i=1, j=2;
    int *a = heap->h;
 
    while(i < heap->n) {
    if ( heap->f[a[i-1]] >= heap->f[a[i]] ) {
        i = j; j++;
    } else {
        swap_(i-1, i);
        i--;
        i = (i==0) ? j++ : i;
    }
    }
}
 
static void _heap_add(heap_t *heap, int c)
{
    if ( (heap->n + 1) > heap->s ) {
    heap->h = realloc(heap->h, heap->s + heap->cs);
    heap->s += heap->cs;
    }
    heap->h[heap->n] = c;
    heap->n++;
    _heap_sort(heap);
}
 
static int _heap_remove(heap_t *heap)
{
    if ( heap->n > 0 ) {
    heap->n--;
    return heap->h[heap->n];
    }
    return -1;
}
 
huffcode_t **create_huffman_codes(long *freqs)
{
    huffcode_t **codes;
    heap_t *heap;
    long efreqs[256*2];
    int preds[256*2];
    int i, extf=256;
    int r1, r2;
 
    memcpy(efreqs, freqs, sizeof(long)*256);
    memset(&efreqs[256], 0, sizeof(long)*256);
 
    heap = _heap_create(256*2, efreqs);
    if ( heap == NULL ) return NULL;
 
    for(i=0; i < 256; i++) if ( efreqs[i] > 0 ) _heap_add(heap, i);
 
    while( heap->n > 1 )
    {
    r1 = _heap_remove(heap);
    r2 = _heap_remove(heap);
    efreqs[extf] = efreqs[r1] + efreqs[r2];
    _heap_add(heap, extf);
    preds[r1] = extf;
    preds[r2] = -extf;
    extf++;
    }
    r1 = _heap_remove(heap);
    preds[r1] = r1;
    _heap_destroy(heap);
 
    codes = malloc(sizeof(huffcode_t *)*256);
 
    int bc, bn, ix;
    for(i=0; i < 256; i++) {
    bc=0; bn=0;
    if ( efreqs[i] == 0 ) { codes[i] = NULL; continue; }
    ix = i;
    while( abs(preds[ix]) != ix ) {
        bc |= ((preds[ix] >= 0) ? 1 : 0 ) << bn;
        ix = abs(preds[ix]);
        bn++;
    }
    codes[i] = malloc(sizeof(huffcode_t));
    codes[i]->nbits = bn;
    codes[i]->code = bc;
    }
    return codes;
}
 
void free_huffman_codes(huffcode_t **c)
{
    int i;
 
    for(i=0; i < 256; i++) free(c[i]);
    free(c);
}
 
void inttobits(int c, int n, char *s)
{
    s[n] = 0;
    while(n > 0) {
    s[n-1] = (c%2) + '0';
    c >>= 1; n--;
    }
}

void* mem_alloc(size_t item_size, size_t n_item)
{
  size_t *x = calloc(1, sizeof(size_t)*2 + n_item * item_size);
  x[0] = item_size;
  x[1] = n_item;
  return x + 2;
}
 
void* mem_extend(void *m, size_t new_n)
{
  size_t *x = (size_t*)m - 2;
  x = realloc(x, sizeof(size_t) * 2 + *x * new_n);
  if (new_n > x[1])
    memset((char*)(x + 2) + x[0] * x[1], 0, x[0] * (new_n - x[1]));
  x[1] = new_n;
  return x + 2;
}
 
inline void _clear(void *m)
{
  size_t *x = (size_t*)m - 2;
  memset(m, 0, x[0] * x[1]);
}
 
#define _new(type, n) mem_alloc(sizeof(type), n)
#define _del(m)   { free((size_t*)(m) - 2); m = 0; }
#define _len(m)   *((size_t*)m - 1)
#define _setsize(m, n)  m = mem_extend(m, n)
#define _extend(m)  m = mem_extend(m, _len(m) * 2)
 
 
typedef uint8_t byte;
typedef uint16_t ushort;
 
#define M_CLR 256 
#define M_EOD 257 
#define M_NEW 258

typedef struct {
  ushort next[256];
} lzw_enc_t;

typedef struct {
  ushort prev, back;
  byte c;
} lzw_dec_t;
 
byte* lzw_encode(byte *in, int max_bits)
{
  int len = _len(in), bits = 9, next_shift = 512;
  ushort code, c, nc, next_code = M_NEW;
  lzw_enc_t *d = _new(lzw_enc_t, 512);
 
  if (max_bits > 15) max_bits = 15;
  if (max_bits < 9 ) max_bits = 12;
 
  byte *out = _new(ushort, 4);
  int out_len = 0, o_bits = 0;
  uint32_t tmp = 0;
 
  inline void write_bits(ushort x) {
    tmp = (tmp << bits) | x;
    o_bits += bits;
    if (_len(out) <= out_len) _extend(out);
    while (o_bits >= 8) {
      o_bits -= 8;
      out[out_len++] = tmp >> o_bits;
      tmp &= (1 << o_bits) - 1;
    }
  }
 
  for (code = *(in++); --len; ) {
    c = *(in++);
    if ((nc = d[code].next[c]))
      code = nc;
    else {
      write_bits(code);
      nc = d[code].next[c] = next_code++;
      code = c;
    }
    if (next_code == next_shift) {
      if (++bits > max_bits) {
        write_bits(M_CLR);
 
        bits = 9;
        next_shift = 512;
        next_code = M_NEW;
        _clear(d);
      } else
        _setsize(d, next_shift *= 2);
    }
  }
 
  write_bits(code);
  write_bits(M_EOD);
  if (tmp) write_bits(tmp);
 
  _del(d);
 
  _setsize(out, out_len);
  return out;
}
 
byte* lzw_decode(byte *in)
{
  byte *out = _new(byte, 4);
  int out_len = 0;
 
  inline void write_out(byte c)
  {
    while (out_len >= _len(out)) _extend(out);
    out[out_len++] = c;
  }
 
  lzw_dec_t *d = _new(lzw_dec_t, 512);
  int len, j, next_shift = 512, bits = 9, n_bits = 0;
  ushort code, c, t, next_code = M_NEW;
 
  uint32_t tmp = 0;
  inline void get_code() {
    while(n_bits < bits) {
      if (len > 0) {
        len --;
        tmp = (tmp << 8) | *(in++);
        n_bits += 8;
      } else {
        tmp = tmp << (bits - n_bits);
        n_bits = bits;
      }
    }
    n_bits -= bits;
    code = tmp >> n_bits;
    tmp &= (1 << n_bits) - 1;
  }
 
  inline void clear_table() {
    _clear(d);
    for (j = 0; j < 256; j++) d[j].c = j;
    next_code = M_NEW;
    next_shift = 512;
    bits = 9;
  };
 
  clear_table();
  for (len = _len(in); len;) {
    get_code();
    if (code == M_EOD) break;
    if (code == M_CLR) {
      clear_table();
      continue;
    }
 
    if (code >= next_code) {
      fprintf(stderr, "Bad sequence\n");
      _del(out);
      goto bail;
    }
 
    d[next_code].prev = c = code;
    while (c > 255) {
      t = d[c].prev; d[t].back = c; c = t;
    }
 
    d[next_code - 1].c = c;
 
    while (d[c].back) {
      write_out(d[c].c);
      t = d[c].back; d[c].back = 0; c = t;
    }
    write_out(d[c].c);
 
    if (++next_code >= next_shift) {
      if (++bits > 16) {
        fprintf(stderr, "Too many bits\n");
        _del(out);
        goto bail;
      }
      _setsize(d, next_shift *= 2);
    }
  }
  if (code != M_EOD) fputs("Bits did not end in EOD\n", stderr);
 
  _setsize(out, out_len);
bail: _del(d);
  return out;
}
